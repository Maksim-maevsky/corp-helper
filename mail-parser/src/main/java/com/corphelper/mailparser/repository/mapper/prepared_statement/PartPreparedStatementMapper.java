package com.corphelper.mailparser.repository.mapper.prepared_statement;

import com.corphelper.mailparser.entity.part.Part;
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
//        ps.setString(4, part.getBrand());
        ps.setTimestamp(5, Timestamp.valueOf(part.getCreateDate()));

    }
}
