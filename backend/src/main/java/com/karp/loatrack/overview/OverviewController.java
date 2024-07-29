package com.karp.loatrack.overview;

import com.karp.loatrack.content.dto.WeeklyClearDto;
import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.overview.dto.OverviewCharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/overview/v1")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class OverviewController {
    private final OverviewService overviewService;

    @GetMapping("/roster")
    public ResponseEntity<List<OverviewCharacterDto>> getRosterInventoryOverview(@RequestParam UUID userId){
        return ResponseEntity.ok().body(overviewService.getAllCharInventoryForOverview(userId));
    }

    @GetMapping("/storage")
    public ResponseEntity<List<OverviewCharacterDto>> getStorageInventoryOverview(@RequestParam UUID userId){
        return ResponseEntity.ok().body(overviewService.getStorageInventoryForOverview(userId));
    }

    @GetMapping("/tier0")
    public ResponseEntity<List<InventoryFlatDto>> getStorageTier0Overview(@RequestParam UUID userId){
        return ResponseEntity.ok().body(overviewService.getStorageTier0ForOverview(userId));
    }
}
