package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.entity.PartInfo;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.repository.mapper.PartRowMapper;
import com.corphelper.mailparser.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.PartInfoQuery;
import com.corphelper.mailparser.repository.query.PartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Repository
public class PartRepositoryImpl implements PartRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public int save(Part part) {

        return jdbcTemplate.update(PartQuery.SAVE_PART_QUERY, part.getId(), part.getCode(), part.getDescription(),
                part.getBrand(), part.getCreateDate());
    }


//    @Override
//    public Part getByCodeAndBrand(String Code, String brand) {
//        String inSql = String.join(",", Collections.nCopies(partStorageNameSet.size(), "?"));
//
//
//        return jdbcTemplate.query(String.format(PartInfoQuery.GET_PARTS_BY_STORAGE_NAME, inSql), partStorageNameSet.toArray(), new PartRowMapper());
//
//    }
}
