package com.corphelper.mailparser.repository.query;

public final class PartInfoQuery {

    private PartInfoQuery(){}

    public static final String SAVE_PART_INFO_QUERY = "INSERT INTO part_infos (id, count, part_storage_id, part_id, create_date) VALUES(?,?,?,?,?)";
    public static final String DELETE_PARTS_INFO_BY_STORAGE_ID = "DELETE FROM part_infos WHERE part_storage_id = ?";
    public static final String GET_PARTS_INFO_BY_STORAGE_NAME = "SELECT parts.id, code, description, create_date, brand," +
            " count, part_storage_id FROM parts LEFT JOIN part_storages ps on parts.part_storage_id = ps.id where ps.name IN (%s)";

}
