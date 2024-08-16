package com.karp.loatrack.calc.dto;

import java.io.Serializable;

public class UpgradeDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int startIlvl;
    public int endIlvl;

    public UpgradeItemDto gold;
    public UpgradeItemDto silver;
    public UpgradeItemDto shards;
    public UpgradeItemDto oreha;
    public UpgradeItemDto leapstone;
    public UpgradeItemDto reds;
    public UpgradeItemDto blues;
}
