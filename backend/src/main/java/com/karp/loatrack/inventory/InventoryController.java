package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.dto.CharInventoryPresentationDto;
import com.karp.loatrack.roster.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inv/v1")
@RequiredArgsConstructor
@Validated
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/")
    public ResponseEntity<List<CharInventoryPresentationDto>> getCharacterInventory(@RequestParam UUID uuid, @RequestParam int charId){
        return ResponseEntity.ok().body(inventoryService.getInventory(uuid, charId));
    }

    @PostMapping("/update")
    public void updateCharacterInventory(@RequestParam UUID uuid, @RequestParam int charId, @RequestBody List<CharInventoryPresentationDto> dto){
        inventoryService.updateCharacterInventory(uuid, charId, dto);
    }
}
