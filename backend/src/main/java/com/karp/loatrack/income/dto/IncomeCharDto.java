package com.karp.loatrack.income.dto;

import com.karp.loatrack.inventory.dto.InventoryFlatDto;

import java.io.Serializable;
import java.util.List;

public class IncomeCharDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int charId;
    public String charName;
    public List<IncomeItemDto> loot;
}
