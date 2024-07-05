package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.inventory.dto.InventoryPresentationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/")
    public ResponseEntity<List<InventoryPresentationDto>> getCharacterInventory(@RequestParam UUID uuid, @RequestParam int charId){
        return ResponseEntity.ok().body(inventoryService.getCharInventory(uuid, charId));
    }

    @GetMapping("/charInv")
    public ResponseEntity<List<InventoryPresentationDto>> getCharacterInventory(@RequestParam UUID uuid, @RequestParam int charId, @RequestParam int tier){
        return ResponseEntity.ok().body(inventoryService.getCharInventoryForTier(uuid, charId, tier));
    }

    @GetMapping("/rosterInv")
    public ResponseEntity<List<InventoryPresentationDto>> getRosterInventory(@RequestParam UUID uuid, @RequestParam int tier){
        return ResponseEntity.ok().body(inventoryService.getRosterInventoryForTier(uuid, tier));
    }


    @PostMapping("/updateCharInv")
    public void updateCharacterInventory(@RequestParam UUID uuid, @RequestParam int charId, @RequestBody List<InventoryFlatDto> dto){
        inventoryService.updateCharacterInventory(uuid, charId, dto);
    }

    @PostMapping("/updateRosterInv")
    public void updateRosterInventory(@RequestParam UUID uuid, @RequestBody List<InventoryFlatDto> dto){
        inventoryService.updateRosterInventory(uuid, dto);
    }
}
