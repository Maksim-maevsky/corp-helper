package com.corphelper.mailparser.repository.mapper;

import com.corphelper.mailparser.entity.PartInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRowMapper implements RowMapper<PartInfo> {


    @Override
    public PartInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        PartInfo part = new PartInfo();

        part.setId(rs.getObject("id", java.util.UUID.class));
        part.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        part.setPartId(rs.getObject("part_id", java.util.UUID.class));
        part.setCount(rs.getInt("count"));
        part.setPartStorageId(rs.getShort("part_storage_id"));

        return part;
    }
}
