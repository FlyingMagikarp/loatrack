package com.karp.loatrack.roster.dto;

import java.io.Serializable;
import java.util.UUID;

public class CharacterDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public UUID userId;
    public String name;
    public int ilvl;
    public String notes;
    public String notesOverview;
    public String classname;
}
