package com.karp.loatrack.util;

import java.util.UUID;

public final class Constants {
    private Constants() {}

    public static final UUID USER_UUID = UUID.fromString("4a3574a2-b173-47b4-a08a-3e6c4f19cab5");

    // Tier IDs
    public static final int TIER_0_ID = 0;
    public static final int TIER_1_ID = 1;
    public static final int TIER_3_ID = 2;
    public static final int TIER_3_1_ID = 3;
    public static final int TIER_3_2_ID = 4;
    public static final int TIER_3_3_ID = 5;
    public static final int TIER_4_ID = 6;
    public static final int TIER_4_1_ID = 7;

    // Tier Names
    public static final String TIER_3_NAME = "T3 Base";
    public static final String TIER_3_1_NAME = "T3 Lower";
    public static final String TIER_3_2_NAME = "T3 Brel";
    public static final String TIER_3_3_NAME = "T3 End";
    public static final String TIER_4_NAME = "T4 Base";

    // Tier iLvl Breakpoints
    public static final int TIER_1_START = 10;
    public static final int TIER_1_END = 1100;

    public static final int TIER_3_START = 1250;
    public static final int TIER_3_END = 1639;

    public static final int TIER_3_1_START = 1250;
    public static final int TIER_3_1_END = 1490;

    public static final int TIER_3_2_START = 1500;
    public static final int TIER_3_2_END = 1580;

    public static final int TIER_3_3_START = 1585;
    public static final int TIER_3_3_END = 1639;

    public static final int TIER_4_START = 1640;

    // Item IDs
    public static final int ITEM_ID_GOLD = 1;
    public static final int ITEM_ID_SILVER = 2;
    public static final int ITEM_ID_T3_SHARDS = 8;
    public static final int ITEM_ID_T3_3_LEAP = 17;
    public static final int ITEM_ID_T3_3_RED = 18;
    public static final int ITEM_ID_T3_3_BLUE = 19;
    public static final int ITEM_ID_T3_2_LEAP = 13;
    public static final int ITEM_ID_T3_2_RED = 14;
    public static final int ITEM_ID_T3_2_BLUE = 15;
    public static final int ITEM_ID_T3_1_LEAP = 9;
    public static final int ITEM_ID_T3_OREHA = 20;

    // Content Type ID
    public static final int CONTENT_TYPE_CD = 1;
    public static final int CONTENT_TYPE_GR = 2;
    public static final int CONTENT_TYPE_RAID = 3;

}