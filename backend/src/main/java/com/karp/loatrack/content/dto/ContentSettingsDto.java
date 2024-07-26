package com.karp.loatrack.content.dto;

import java.io.Serializable;
import java.util.List;

public class ContentSettingsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int charId;
    public List<Integer> raidIds;
    public boolean clearCD;
    public boolean restedCD;
    public boolean clearGR;
    public boolean restedGR;
    public int unasLeapstone;
}
