package com.karp.loatrack.content;

import com.karp.loatrack.content.dto.ContentDto;
import com.karp.loatrack.content.dto.ContentSettingsDto;
import com.karp.loatrack.content.dto.WeeklyClearDto;
import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.content.model.ContentSettings;
import com.karp.loatrack.content.model.WeeklyClearOV;
import com.karp.loatrack.roster.RosterService;
import com.karp.loatrack.roster.dto.CharacterDto;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService {
    private final ContentRepository contentRepository;
    private final ContentTypeRepository contentTypeRepository;
    private final WeeklyClearRepository weeklyClearRepository;
    private final ContentSettingsRepository contentSettingsRepository;
    private final RosterService rosterService;

    public List<ContentDto> getClearableRaids(int iLvl){
        List<Content> clearableRaids = contentRepository.findByILvl(iLvl);
        return mapContentToDto(clearableRaids);
    }

    public List<List<WeeklyClearDto>> getWeeklyClearStatus(){
        List<CharacterDto> allCharacters = rosterService.getAllCharacters(Constants.USER_UUID);
        List<List<WeeklyClearDto>> weeklyClearDtos = new ArrayList<>();
        for(CharacterDto characterDto : allCharacters){
            weeklyClearDtos.add(getWeeklyClearStatusForCharId(characterDto.id));
        }

        return weeklyClearDtos;
    }

    public List<WeeklyClearDto> getWeeklyClearStatusForCharId(int charId){
        List<WeeklyClearOV> wcs = weeklyClearRepository.findByCharId(charId);
        return mapWeeklyClearToDto(wcs);
    }

    public void updateWeeklyClearStatus(WeeklyClearDto clear){
        weeklyClearRepository.updateWeeklyClear(clear.cleared, clear.charId, clear.raidId);
    }

    public void resetWeeklyClearStatus(){
        weeklyClearRepository.resetWeeklyClear();
    }

    public ContentSettingsDto getContentSettings(int charId){
        List<ContentSettings> cs = contentSettingsRepository.findSettingsByCharId(charId);
        List<ContentSettingsDto> csDto = mapContentSettingsToDto(cs);
        return csDto.isEmpty() ? new ContentSettingsDto() : csDto.getFirst();
    }

    public void updateContentSettings(ContentSettingsDto contentSettingsDto){
        List<ContentSettings> cs = contentSettingsRepository.findSettingsByCharId(contentSettingsDto.charId);
        if (cs.isEmpty()) {
            saveNewContentSettingsFromDto(contentSettingsDto);
        } else {
            updateContentSettingsFromDto(cs.getFirst(), contentSettingsDto);
        }
        updateWeeklyRaidSettings(contentSettingsDto.raidIds, contentSettingsDto.charId);
    }

    private void updateWeeklyRaidSettings(List<Integer> raidIds, int charId){
        weeklyClearRepository.deleteByCharId(charId);

        Character character = rosterService.getCharacterById(charId);
        List<Content> raidList = new ArrayList<>();
        for (Integer raid : raidIds) {
            if (raid != -1){
                Content content = contentRepository.findById(raid).get();
                raidList.add(content);
            }
        }

        for (Content content : raidList) {
            WeeklyClearOV wc = new WeeklyClearOV();
            wc.setFkChar(character);
            wc.setFkRaid(content);
            wc.setCleared(false);

            weeklyClearRepository.save(wc);
        }
    }

    private void updateContentSettingsFromDto(ContentSettings cs, ContentSettingsDto dto){
        cs.setClearCd(dto.clearCD);
        cs.setRestedCd(dto.restedCD);
        cs.setClearGr(dto.clearGR);
        cs.setRestedGr(dto.restedGR);
        cs.setUnasLs(dto.unasLeapstone);

        contentSettingsRepository.save(cs);
    }

    private void saveNewContentSettingsFromDto(ContentSettingsDto dto){
        ContentSettings cs = new ContentSettings();
        cs.setFkChar(rosterService.getCharacterById(dto.charId));
        cs.setClearCd(dto.clearCD);
        cs.setRestedCd(dto.restedCD);
        cs.setClearGr(dto.clearGR);
        cs.setRestedGr(dto.restedGR);
        cs.setUnasLs(dto.unasLeapstone);

        contentSettingsRepository.save(cs);
    }

    private List<ContentSettingsDto> mapContentSettingsToDto(List<ContentSettings> contentSettings){
        List<ContentSettingsDto> contentSettingsDtos = new ArrayList<>();
        for(ContentSettings contentSetting : contentSettings){
            ContentSettingsDto dto = new ContentSettingsDto();
            dto.charId = contentSetting.getFkChar().getId();
            dto.clearCD = contentSetting.isClearCd();
            dto.restedCD = contentSetting.isRestedCd();
            dto.clearGR = contentSetting.isClearGr();
            dto.restedGR = contentSetting.isRestedGr();
            dto.unasLeapstone = contentSetting.getUnasLs();

            List<WeeklyClearDto> raids = getWeeklyClearStatusForCharId(contentSetting.getFkChar().getId());
            dto.raidIds = raids.stream().map(x -> x.raidId).toList();

            contentSettingsDtos.add(dto);
        }

        return contentSettingsDtos;
    }

    private List<ContentDto> mapContentToDto(List<Content> contentList) {
        List<ContentDto> contentDtoList = new ArrayList<>();
        for (Content content : contentList) {
            ContentDto dto = new ContentDto();
            dto.id = content.getId();
            dto.name = content.getName();
            dto.hardmode = content.isHardmode();
            contentDtoList.add(dto);
        }

        return contentDtoList;
    }

    private List<WeeklyClearDto> mapWeeklyClearToDto(List<WeeklyClearOV> weeklyClearList){
        List<WeeklyClearDto> weeklyClearDtoList = new ArrayList<>();
        for (WeeklyClearOV weeklyClearOV : weeklyClearList) {
            WeeklyClearDto dto = new WeeklyClearDto();
            dto.charId = weeklyClearOV.getFkChar().getId();
            dto.charName = rosterService.getCharacterById(weeklyClearOV.getFkChar().getId()).getName();
            dto.raidId = weeklyClearOV.getFkRaid().getId();
            dto.raidName = weeklyClearOV.getFkRaid().getName();
            dto.hardmode = weeklyClearOV.getFkRaid().isHardmode();
            dto.cleared = weeklyClearOV.isCleared();
            weeklyClearDtoList.add(dto);
        }

        return weeklyClearDtoList;
    }
}
