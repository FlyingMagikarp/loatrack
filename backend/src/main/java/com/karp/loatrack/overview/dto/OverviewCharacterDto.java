package com.karp.loatrack.overview.dto;

import com.karp.loatrack.inventory.dto.InventoryFlatDto;

import java.io.Serializable;
import java.util.List;

public class OverviewCharacterDto implements Serializable{
    private static final long serialVersionUID = 1L;

    public int charId;
    public String name;
    public List<InventoryFlatDto> inventory;
}
