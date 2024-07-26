package com.karp.loatrack.content;

import com.karp.loatrack.content.dto.ContentDto;
import com.karp.loatrack.content.dto.ContentSettingsDto;
import com.karp.loatrack.content.dto.WeeklyClearDto;
import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.content.model.ContentSettings;
import com.karp.loatrack.content.model.WeeklyClearOV;
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

    public List<ContentDto> getClearableRaids(int iLvl){
        List<Content> clearableRaids = contentRepository.findByILvl(iLvl);
        return mapContentToDto(clearableRaids);
    }

    public List<WeeklyClearDto> getWeeklyClearStatus(int charId){
        List<WeeklyClearOV> wcs = weeklyClearRepository.findByCharId(charId);
        return mapWeeklyClearToDto(wcs);
    }

    public ContentSettingsDto getContentSetting(int charId){
        List<ContentSettings> cs = contentSettingsRepository.findSettingsByCharId(charId);
        List<ContentSettingsDto> csDto = mapContentSettingsToDto(cs);
        return csDto.isEmpty() ? new ContentSettingsDto() : csDto.getFirst();
    }

    public Content getHighestClearableCD(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_CD, iLvl);
        return cdList.getLast();
    }

    public Content getHighestClearableGR(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_GR, iLvl);
        return cdList.getLast();
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

            List<WeeklyClearDto> raids = getWeeklyClearStatus(contentSetting.getFkChar().getId());
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
            dto.raidId = weeklyClearOV.getFkRaid().getId();
            dto.raidName = weeklyClearOV.getFkRaid().getName();
            dto.cleared = weeklyClearOV.isCleared();
            weeklyClearDtoList.add(dto);
        }

        return weeklyClearDtoList;
    }
}
