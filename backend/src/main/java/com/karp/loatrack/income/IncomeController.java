package com.karp.loatrack.income;

import com.karp.loatrack.income.dto.IncomeCharDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/income/v1")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class IncomeController {
    private final IncomeService incomeService;

    @GetMapping("/")
    public List<IncomeCharDto> getRosterIncome(){
        return incomeService.getRosterIncome();
    }
}
