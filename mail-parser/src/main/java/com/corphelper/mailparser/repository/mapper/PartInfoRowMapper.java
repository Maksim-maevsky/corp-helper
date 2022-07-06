package com.corphelper.mailparser.repository.mapper;

import com.corphelper.mailparser.entity.part.PartInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartInfoRowMapper implements RowMapper<PartInfo> {


    @Override
    public PartInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        PartInfo partInfo = new PartInfo();

        partInfo.setId(rs.getObject("id", java.util.UUID.class));
        partInfo.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        //partInfo.setPart(rs.getObject("part_id", java.util.UUID.class));
        partInfo.setCount(rs.getInt("count"));
        partInfo.setPartStorageId(rs.getShort("part_storage_id"));

        return partInfo;
    }
}
