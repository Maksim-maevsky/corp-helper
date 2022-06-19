package com.corphelper.mailparser.repository.mapper.prepared_statement;

import com.corphelper.mailparser.entity.Part;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class PartPreparedStatementMapper {

    public void mapPreparedStatement(PreparedStatement ps, Part part) throws SQLException {

        ps.setObject(1, part.getId());
        ps.setString(2, part.getCode());
        ps.setString(3, part.getDescription());
        ps.setTimestamp(4, Timestamp.valueOf(part.getCreateDate()));
        ps.setString(5, part.getBrand());
        ps.setInt(6, part.getCount());
        ps.setShort(7, part.getPartStorageId());

    }
}
