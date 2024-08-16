package com.karp.loatrack.calc.dto;

import java.io.Serializable;

public class UpgradeItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int itemId;
    public String itemName;
    public int base;
    public int have;
    public int income;
    public int diff;
    public int time;
}
