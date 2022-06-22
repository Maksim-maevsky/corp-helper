package com.corphelper.mailparser.repository.query;

public final class PartQuery {

    private PartQuery(){}

    public static final String SAVE_PART_QUERY = "INSERT INTO parts(id, code, description, create_date, brand, count, part_storage_id) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_PARTS_BY_STORAGE_ID = "DELETE FROM parts WHERE part_storage_id = ?";
    public static final String GET_PARTS_BY_STORAGE_NAME = "SELECT parts.id, code, description, create_date, brand," +
            " count, part_storage_id FROM parts LEFT JOIN part_storages ps on parts.part_storage_id = ps.id where ps.name IN (%s)";

}
