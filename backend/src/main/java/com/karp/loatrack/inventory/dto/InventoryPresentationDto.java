package com.karp.loatrack.inventory.dto;

import com.karp.loatrack.item.model.Item;

import java.io.Serializable;

public class InventoryPresentationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public Item item;
    public int amount;
}
