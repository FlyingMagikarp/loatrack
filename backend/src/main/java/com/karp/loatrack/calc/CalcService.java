package com.karp.loatrack.calc;

import com.karp.loatrack.calc.dto.CalcDto;
import com.karp.loatrack.income.IncomeService;
import com.karp.loatrack.inventory.InventoryService;
import com.karp.loatrack.inventory.dto.InventoryFlatDto;
import com.karp.loatrack.roster.RosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalcService {
    private final RosterService rosterService;
    private final InventoryService inventoryService;
    private final IncomeService incomeService;


    //get items for tier char is in
    // red/blue
    // leaps
    // honor shards
    // gold
    // silver
    public List<InventoryFlatDto> getItems(int charId){
        return new ArrayList<>();
    }


    // take matList
    // get roster income
    // do math to get roster+bound
    // subtract mats in inventory char + roster
    public List<CalcDto> getCalculations(int charId, List<InventoryFlatDto> matList){
        return new ArrayList<>();
    }
}
