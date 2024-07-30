package com.karp.loatrack.income.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class IncomeItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter public int itemId;
    public String itemName;
    public boolean bound;
    public int amount;

    public IncomeItemDto(){}

    public IncomeItemDto(IncomeItemDto other) {
        this.itemId = other.itemId;
        this.itemName = other.itemName;
        this.bound = other.bound;
        this.amount = other.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeItemDto itemDto = (IncomeItemDto) o;
        return itemId == itemDto.itemId && bound == itemDto.bound;
    }

    @Override
    public int hashCode(){
        return Objects.hash(itemId, bound);
    }

}
