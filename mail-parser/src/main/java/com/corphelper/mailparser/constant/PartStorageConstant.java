package com.corphelper.mailparser.constant;

import com.corphelper.mailparser.entity.PartStorage;

import java.util.Map;

public final class PartStorageConstant {

    private PartStorageConstant() {
    }

    public static final short TANGDE_STORAGE_ID = 1;
    public static final short PLASTIC_STORAGE_ID = 2;
    public static final short MIKHNEVO_STORAGE_ID = 3;

    public static final PartStorage TANGDE_STORAGE = new PartStorage(TANGDE_STORAGE_ID, "TANGDE");
    public static final PartStorage PLASTIC_STORAGE = new PartStorage(PLASTIC_STORAGE_ID, "PLASTIC");
    public static final PartStorage MIKHNEVO_STORAGE = new PartStorage(MIKHNEVO_STORAGE_ID, "MIKHNEVO");

    public static final Map<String, PartStorage> PART_STORAGE_MAP = Map.of("mikhnevo", MIKHNEVO_STORAGE,
            "tangde", TANGDE_STORAGE,
            "plastic", PLASTIC_STORAGE);

}
