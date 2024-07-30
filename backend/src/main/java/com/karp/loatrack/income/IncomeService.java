package com.karp.loatrack.income;

import com.karp.loatrack.content.ContentRepository;
import com.karp.loatrack.content.ContentService;
import com.karp.loatrack.content.dto.ContentSettingsDto;
import com.karp.loatrack.content.dto.WeeklyClearDto;
import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.income.dto.IncomeCharDto;
import com.karp.loatrack.income.dto.IncomeItemDto;
import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.item.model.Item;
import com.karp.loatrack.loot.LootService;
import com.karp.loatrack.loot.model.Loot;
import com.karp.loatrack.roster.RosterService;
import com.karp.loatrack.roster.dto.CharacterDto;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeService {
    private final ContentRepository contentRepository;
    private final ContentService contentService;
    private final LootService lootService;
    private final RosterService rosterService;

    private Content getHighestClearableCD(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_CD, iLvl);
        return cdList.getLast();
    }

    private Content getHighestClearableGR(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_GR, iLvl);
        return cdList.getLast();
    }

    private IncomeItemDto getGoldFromRaids(int charId){
        List<WeeklyClearDto> weeklyRaids = contentService.getWeeklyClearStatusForCharId(charId);
        List<Loot> loot = new ArrayList<>();
        for(WeeklyClearDto dto : weeklyRaids){
            loot.addAll(lootService.getLootForContent(dto.raidId));
        }

        IncomeItemDto dto = new IncomeItemDto();
        dto.itemId = Constants.ITEM_ID_GOLD;
        dto.itemName = "Gold";
        dto.bound = false;
        dto.amount = loot.stream().mapToInt(Loot::getAmount).sum();

        return dto;
    }

    public List<IncomeCharDto> getRosterIncome(){
        List<CharacterDto> allCharacters = rosterService.getAllCharacters(Constants.USER_UUID);
        List<IncomeCharDto> incomeCharDtos = new ArrayList<>();
        for(CharacterDto character : allCharacters){
            incomeCharDtos.add(getCharacterIncome(character));
        }

        List<IncomeItemDto> tmp = new ArrayList<>();
        for(IncomeCharDto incomeChar : incomeCharDtos){
            tmp.addAll(incomeChar.loot.stream().filter(x -> !x.bound).toList());
        }

        IncomeCharDto rosterIncDto = new IncomeCharDto();
        rosterIncDto.charId = -1;
        rosterIncDto.charName = "Roster";
        rosterIncDto.loot = combineDuplicateIncomeItems(tmp);

        incomeCharDtos.addFirst(rosterIncDto);

        return incomeCharDtos;
    }

    private IncomeCharDto getCharacterIncome(CharacterDto characterDto){
        IncomeCharDto incomeCharDto = new IncomeCharDto();
        incomeCharDto.charId = characterDto.id;
        incomeCharDto.charName = characterDto.name;

        ContentSettingsDto contentSettings = contentService.getContentSettings(characterDto.id);

        List<IncomeItemDto> lootCD = mapLootToInventoryFlatDto(lootService.getLootForContent(getHighestClearableCD(characterDto.ilvl).getId()));
        List<IncomeItemDto> lootGR = mapLootToInventoryFlatDto(lootService.getLootForContent(getHighestClearableGR(characterDto.ilvl).getId()));
        IncomeItemDto lootGold = getGoldFromRaids(characterDto.id);
        IncomeItemDto lootUna = getIncomeItemDtoForUnas(characterDto.ilvl, contentSettings.unasLeapstone);
        lootUna.amount *= 7;

        List<IncomeItemDto> inv = new ArrayList<>();
        inv.add(lootGold);
        if(lootUna.amount > 0){
            inv.add(lootUna);
        }
        if(contentSettings.clearCD){
            // twice since 2x CD per day
            inv.addAll(multiplyForWeekWithRest(lootCD, contentSettings.restedCD));
            inv.addAll(lootCD);
        }

        if(contentSettings.clearGR){
            inv.addAll(multiplyForWeekWithRest(lootGR, contentSettings.restedGR));
        }

        incomeCharDto.loot = combineDuplicateIncomeItems(inv);

        return incomeCharDto;
    }

    private List<IncomeItemDto> multiplyForWeekWithRest(List<IncomeItemDto> incomeItemDtos, boolean rested){
        double factor = rested ? 0.66 : 1;
        return incomeItemDtos.stream().peek(x -> x.amount = (int)(x.amount*7*factor)).toList();
    }

    private List<IncomeItemDto> combineDuplicateIncomeItems(List<IncomeItemDto> incomeItemDtos){
        Map<IncomeItemDto, Integer> combinedItems = new HashMap<>();
        for (IncomeItemDto dto : incomeItemDtos){
            IncomeItemDto copy = new IncomeItemDto(dto);
            combinedItems.merge(copy, copy.amount, Integer::sum);
        }

        return combinedItems.entrySet().stream()
                .map(entry -> {
                    IncomeItemDto dto = entry.getKey();
                    Integer amount = entry.getValue();
                    dto.amount = amount;

                    return new IncomeItemDto(dto);
                })
                .sorted(Comparator.comparingInt(IncomeItemDto::getItemId)).toList();
    }

    private List<IncomeItemDto> mapLootToInventoryFlatDto(List<Loot> loot){
        List<IncomeItemDto> inv = new ArrayList<>();

        for(Loot l : loot){
            IncomeItemDto dto = new IncomeItemDto();
            dto.itemId = l.getFkItem().getId();
            dto.itemName = l.getFkItem().getName();
            dto.bound = l.isBound();
            dto.amount = l.getAmount();
            inv.add(dto);
        }

        return inv;
    }

    private IncomeItemDto getIncomeItemDtoForUnas(int ilvl, int amount){
        Item ls = lootService.getLeapstoneForIlvl(ilvl);
        IncomeItemDto lootUna = new IncomeItemDto();
        lootUna.itemId = ls.getId();
        lootUna.itemName = ls.getName();
        lootUna.amount = amount;
        lootUna.bound = true;

        return lootUna;
    }
}
