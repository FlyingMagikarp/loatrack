package com.karp.loatrack.overview;

import com.karp.loatrack.inventory.CharInventoryRepository;
import com.karp.loatrack.inventory.RosterInventoryRepository;
import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.inventory.model.CharInventory;
import com.karp.loatrack.inventory.model.RosterInventory;
import com.karp.loatrack.overview.dto.OverviewCharacterDto;
import com.karp.loatrack.roster.RosterRepository;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OverviewService {
    private final RosterRepository rosterRepository;
    private final CharInventoryRepository charInventoryRepository;
    private final RosterInventoryRepository rosterInventoryRepository;

    public List<InventoryFlatDto> getStorageTier0ForOverview(UUID userId){
        ArrayList<Integer> itemIds = new ArrayList<>();
        itemIds.add(Constants.ITEM_ID_GOLD);
        itemIds.add(Constants.ITEM_ID_SILVER);

        return itemIds.stream().map(itemId -> mapRosterInvToFlatDto(rosterInventoryRepository.findRosterInventoryByItemId(userId, itemId))).toList();
    }

    public List<OverviewCharacterDto> getStorageInventoryForOverview(UUID userId){
        ArrayList<Integer> tierIds = new ArrayList<>();
        tierIds.add(Constants.TIER_3_3_ID);
        tierIds.add(Constants.TIER_3_2_ID);

        List<OverviewCharacterDto> dtos = new ArrayList<>();
        for (int tierId : tierIds){
            List<Integer> itemIds = getItemIdsForOverview(mapTierIdToIlvl(tierId));
            OverviewCharacterDto dto = new OverviewCharacterDto();
            dto.charId = tierId;
            dto.name = mapTierIdtoName(tierId);
            dto.inventory = itemIds.stream().map(itemId -> mapRosterInvToFlatDto(rosterInventoryRepository.findRosterInventoryByItemId(userId, itemId))).toList();
            dtos.add(dto);
        }

        return dtos;
    }

    public List<OverviewCharacterDto> getAllCharInventoryForOverview(UUID userId){
        List<Character> chars = rosterRepository.findAllByUserId(userId);
        List<OverviewCharacterDto> charInventoryDtos = new ArrayList<>();

        for (Character character : chars) {
            charInventoryDtos.add(getCharInventoryForOverview(userId, character.getId()));
        }

        return charInventoryDtos;
    }

    private OverviewCharacterDto getCharInventoryForOverview(UUID userId, int charId) {
        Character c = rosterRepository.findById(charId).get();
        if (!c.getFkUser().getId().equals(userId)) {
            return null;
        }
        OverviewCharacterDto oc = new OverviewCharacterDto();
        oc.charId = c.getId();
        oc.name = c.getName();

        ArrayList<InventoryFlatDto> inventory = new ArrayList<>();
        List<Integer> itemIds = getItemIdsForOverview(c.getIlvl());
        for (Integer itemId : itemIds) {
            CharInventory ci = charInventoryRepository.findCharInvPosByItemId(c.getId(), itemId);
            inventory.add(mapCharInvToFlatDto(ci));
        }

        oc.inventory = inventory;

        return oc;
    }

    private ArrayList<Integer> getItemIdsForOverview(int ilvl){
        ArrayList<Integer> itemIds = new ArrayList<>();

        // t3 check
        if(ilvl > Constants.TIER_3_START && ilvl < Constants.TIER_3_END){
            itemIds.add(Constants.ITEM_ID_T3_SHARDS);

            // second t3 mats, < akkan
            if (ilvl < Constants.TIER_3_2_END) {
                itemIds.add(Constants.ITEM_ID_T3_2_LEAP);
                itemIds.add(Constants.ITEM_ID_T3_2_RED);
                itemIds.add(Constants.ITEM_ID_T3_2_BLUE);
            }
            // last t3 mats, > akkan until t4
            if (ilvl >= Constants.TIER_3_2_END) {
                itemIds.add(Constants.ITEM_ID_T3_3_LEAP);
                itemIds.add(Constants.ITEM_ID_T3_3_RED);
                itemIds.add(Constants.ITEM_ID_T3_3_BLUE);
            }
        }

        return itemIds;
    }

    private InventoryFlatDto mapCharInvToFlatDto(CharInventory ci){
        InventoryFlatDto dto = new InventoryFlatDto();
        dto.itemId = ci.getFkItem().getId();
        dto.itemName = ci.getFkItem().getName();
        dto.amount = ci.getAmount();

        return dto;
    }

    private InventoryFlatDto mapRosterInvToFlatDto(RosterInventory ri){
        InventoryFlatDto dto = new InventoryFlatDto();
        dto.itemId = ri.getFkItem().getId();
        dto.itemName = ri.getFkItem().getName();
        dto.amount = ri.getAmount();

        return dto;
    }

    private String mapTierIdtoName(int tierId){
        if (Constants.TIER_3_3_ID == tierId){
            return Constants.TIER_3_3_NAME;
        }

        if (Constants.TIER_3_2_ID == tierId){
            return Constants.TIER_3_2_NAME;
        }

        if (Constants.TIER_3_1_ID == tierId){
            return Constants.TIER_3_1_NAME;
        }

        if (Constants.TIER_3_ID == tierId){
            return Constants.TIER_3_NAME;
        }

        return "";
    }

    private int mapTierIdToIlvl(int tierId){
        if (Constants.TIER_3_3_ID == tierId){
            return Constants.TIER_3_3_START;
        }

        if (Constants.TIER_3_2_ID == tierId){
            return Constants.TIER_3_2_START;
        }

        if (Constants.TIER_3_1_ID == tierId){
            return Constants.TIER_3_1_START;
        }

        if (Constants.TIER_3_ID == tierId){
            return Constants.TIER_3_START;
        }

        return 0;
    }

}
