package com.karp.loatrack.calc;

import com.karp.loatrack.calc.dto.CalcDto;
import com.karp.loatrack.inventory.dto.InventoryFlatDto;
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

@RestController
@RequestMapping("/calc/v1")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class CalcController {
    private final CalcService calcService;

    @GetMapping("/items")
    public ResponseEntity<List<InventoryFlatDto>> getItems(@RequestParam int charId){
        return ResponseEntity.ok().body(calcService.getItems(charId));
    }

    @PostMapping("/")
    public ResponseEntity<List<CalcDto>> getCalculations(@RequestParam int charId, @RequestBody List<InventoryFlatDto> matList){
        return ResponseEntity.ok().body(calcService.getCalculations(charId, matList));
    }
}
