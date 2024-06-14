package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.dto.CharInventoryPresentationDto;
import com.karp.loatrack.inventory.model.CharInventory;
import com.karp.loatrack.roster.RosterRepository;
import com.karp.loatrack.roster.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final CharInventoryRepository charInventoryRepository;
    private final RosterRepository rosterRepository;

    public List<CharInventoryPresentationDto> getInventory(UUID userId, int charId) {
        if (rosterRepository.findById(charId).get().getFkUser().getId() != userId) {
            return null;
        }

        List<CharInventory> charInventories = charInventoryRepository.findAllByCharacterId(charId);
        List<CharInventoryPresentationDto> charInventoryPresentationDtos = new ArrayList<>();
        for (CharInventory charInventory : charInventories) {
            CharInventoryPresentationDto dto = new CharInventoryPresentationDto();

            dto.item = charInventory.getFkItem();
            dto.amount = charInventory.getAmount();
        }

        return charInventoryPresentationDtos;
    }

    public void updateCharacterInventory(UUID userId, int charId, List<CharInventoryPresentationDto> dto) {
        if (rosterRepository.findById(charId).get().getFkUser().getId() != userId) {
            return;
        }

        for (CharInventoryPresentationDto cip : dto) {
            CharInventory charInventoryToUpdate = charInventoryRepository.findCharInventoryToUpdate(charId, cip.item.getId());
            charInventoryToUpdate.setAmount(cip.amount);
            charInventoryRepository.save(charInventoryToUpdate);
        }
    }
}
