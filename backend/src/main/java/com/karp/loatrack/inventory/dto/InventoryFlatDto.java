package com.karp.loatrack.inventory.dto;

import java.io.Serializable;

public class InventoryFlatDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int itemId;
    public String itemName;
    public int amount;
}
