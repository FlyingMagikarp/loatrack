package com.karp.loatrack.content.dto;

import java.io.Serializable;

public class WeeklyClearDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int charId;
    public String charName;
    public int raidId;
    public String raidName;
    public boolean hardmode;
    public boolean cleared;
}
