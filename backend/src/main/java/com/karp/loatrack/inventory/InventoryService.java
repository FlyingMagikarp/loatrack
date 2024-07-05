package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.inventory.dto.InventoryPresentationDto;
import com.karp.loatrack.inventory.model.CharInventory;
import com.karp.loatrack.inventory.model.RosterInventory;
import com.karp.loatrack.item.ItemRepository;
import com.karp.loatrack.item.model.Item;
import com.karp.loatrack.roster.RosterRepository;
import com.karp.loatrack.roster.UserRepository;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final CharInventoryRepository charInventoryRepository;
    private final RosterInventoryRepository rosterInventoryRepository;
    private final RosterRepository rosterRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<InventoryPresentationDto> getCharInventory(UUID userId, int charId) {
        if (!rosterRepository.findById(charId).get().getFkUser().getId().equals(userId)) {
            return null;
        }

        List<CharInventory> charInventories = charInventoryRepository.findAllByCharacterId(charId);
        List<InventoryPresentationDto> charInventoryPresentationDtos = new ArrayList<>();
        for (CharInventory charInventory : charInventories) {
            InventoryPresentationDto dto = new InventoryPresentationDto();

            dto.item = charInventory.getFkItem();
            dto.amount = charInventory.getAmount();

            charInventoryPresentationDtos.add(dto);
        }

        return charInventoryPresentationDtos;
    }

    public List<InventoryPresentationDto> getRosterInventory(UUID userId) {
        List<RosterInventory> rosterInventories = rosterInventoryRepository.findAllRosterInventoryByUserId(userId);
        List<InventoryPresentationDto> rosterInventoryPresentationDtos = new ArrayList<>();
        for (RosterInventory rosterInventory : rosterInventories) {
            InventoryPresentationDto dto = new InventoryPresentationDto();

            dto.item = rosterInventory.getFkItem();
            dto.amount = rosterInventory.getAmount();
            rosterInventoryPresentationDtos.add(dto);
        }

        return rosterInventoryPresentationDtos;
    }

    public List<InventoryPresentationDto> getCharInventoryForTier(UUID userId, int charId, int tier) {
        if (Constants.TIER_3_ID == tier){
            return getCharInventoryForTier3(userId, charId, tier);
        }

        return getCharInventory(userId, charId)
                .stream()
                .filter(item -> item.item.getFkTier().getId() == tier)
                .collect(Collectors.toList());
    }

    public List<InventoryPresentationDto> getRosterInventoryForTier(UUID userId, int tier) {
        List<Integer> allTier3Ids = new ArrayList<>();
        if (Constants.TIER_3_ID == tier){
            allTier3Ids.add(2);
            allTier3Ids.add(3);
            allTier3Ids.add(4);
            allTier3Ids.add(5);
        } else {
            allTier3Ids.add(tier);
        }

        return getRosterInventory(userId)
                .stream()
                .filter(item -> allTier3Ids.contains( item.item.getFkTier().getId()))
                .collect(Collectors.toList());
    }

    public List<InventoryPresentationDto> getCharInventoryForTier3(UUID userId, int charId, int tier) {
        Character character = rosterRepository.findById(charId).get();

        List<Integer> allTier3Ids = new ArrayList<>();
        allTier3Ids.add(2);

        if (character.getIlvl() >= Constants.TIER_3_2_END){
            allTier3Ids.add(5);
        } else if (character.getIlvl() >= Constants.TIER_3_1_END){
            allTier3Ids.add(4);
        } else {
            allTier3Ids.add(3);
        }

        return getCharInventory(userId, charId)
                .stream()
                .filter(item -> allTier3Ids.contains(item.item.getFkTier().getId()))
                .collect(Collectors.toList());
    }

    public void updateCharacterInventory(UUID userId, int charId, List<InventoryFlatDto> dto) {
        if (!rosterRepository.findById(charId).get().getFkUser().getId().equals(userId)) {
            return;
        }

        for (InventoryFlatDto cip : dto) {
            CharInventory charInventoryToUpdate = charInventoryRepository.findCharInvPosByItemId(charId, cip.itemId);
            charInventoryToUpdate.setAmount(cip.amount);
            charInventoryRepository.save(charInventoryToUpdate);
        }
    }

    public void updateRosterInventory(UUID userId, List<InventoryFlatDto> dto){
        for (InventoryFlatDto item : dto){
            RosterInventory rosterInventoryToUpdate = rosterInventoryRepository.findRosterInventoryByItemId(userId, item.itemId);
            rosterInventoryToUpdate.setAmount(item.amount);
            rosterInventoryRepository.save(rosterInventoryToUpdate);
        }
    }

    public void initCharacterInventory(int charId){
        List<Item> allItems = itemRepository.findAll();
        // remove tier0 items, those are always roster bound (gold, silver)
        List<Item> allCharItems = allItems.stream().filter(i -> i.getFkTier().getId() != Constants.TIER_0_ID).toList();
        Character character = rosterRepository.findById(charId).get();

        for (Item item : allCharItems) {
            CharInventory ci = new CharInventory();

            ci.setFkItem(item);
            ci.setAmount(0);
            ci.setFkCharacter(character);

            charInventoryRepository.save(ci);
        }

        List<RosterInventory> rosterInv = rosterInventoryRepository.findAllRosterInventoryByUserId(Constants.USER_UUID);
        if (rosterInv == null || rosterInv.isEmpty()){
            initRosterInventory();
        }
    }

    private void initRosterInventory(){
        // Ids of character bound items
        // free tap items are never unbound
        List<Integer> charBoundItemIds = Arrays.asList(150, 151, 152);

        List<Item> allItems = itemRepository.findAll();
        List<Item> allRosterItems = allItems.stream().filter(i -> !charBoundItemIds.contains(i.getId())).toList();

        for (Item item : allRosterItems) {
            RosterInventory ri = new RosterInventory();

            ri.setFkItem(item);
            ri.setAmount(0);
            ri.setFkUser(userRepository.findById(Constants.USER_UUID).get());

            rosterInventoryRepository.save(ri);
        }
    }
}
