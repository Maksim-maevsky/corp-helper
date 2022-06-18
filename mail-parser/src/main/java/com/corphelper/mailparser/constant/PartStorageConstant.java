package com.corphelper.mailparser.constant;

import com.corphelper.mailparser.entity.PartStorage;

import java.util.Map;

public final class PartStorageConstant {

    private PartStorageConstant() {
    }

    public static final PartStorage TANGDE_STORAGE = new PartStorage((short) 1, "TANGDE");
    public static final PartStorage PLASTIC_STORAGE = new PartStorage((short) 2, "PLASTIC");
    public static final PartStorage MIKHNEVO_STORAGE = new PartStorage((short) 3, "MIKHNEVO");

    public static final Map<String, PartStorage> PART_STORAGE_MAP = Map.of("mikhnevo", MIKHNEVO_STORAGE,
            "tangde", TANGDE_STORAGE,
            "plastic", PLASTIC_STORAGE);

}
