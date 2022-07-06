package com.corphelper.mailparser.repository.mapper;

import com.corphelper.mailparser.entity.part.Part;
import com.corphelper.mailparser.entity.part.PartInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRowMapper implements RowMapper<Part> {


    @Override
    public Part mapRow(ResultSet rs, int rowNum) throws SQLException {

        Part part = new Part();

        part.setId(rs.getObject("id", java.util.UUID.class));
        part.setCode(rs.getString("code"));
        part.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        //partInfo.setPart(rs.getObject("part_id", java.util.UUID.class));
//        part.setCount(rs.getInt("count"));
//        part.setPartStorageId(rs.getShort("part_storage_id"));

        return part;
    }
}
