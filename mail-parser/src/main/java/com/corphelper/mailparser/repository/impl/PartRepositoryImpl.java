package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.repository.mapper.PartRowMapper;
import com.corphelper.mailparser.repository.mapper.prepared_statement.PartPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.PartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    private final PartPreparedStatementMapper partPreparedStatementMapper;


    @Override
    public void delete(short storagePartId) {

        Object[] args = new Object[]{storagePartId};
        jdbcTemplate.update(PartQuery.DELETE_PARTS_BY_STORAGE_ID, args);

    }

    @Override
    public int save(Part part) {

        return jdbcTemplate.update(PartQuery.SAVE_PART_QUERY, part.getId(), part.getCode(), part.getDescription(), part.getCreateDate(),
                part.getBrand(), part.getCount(), part.getPartStorageId());
    }

    @Override
    public int[] saveAll(List<Part> parts) {

        return this.jdbcTemplate.batchUpdate(PartQuery.SAVE_PART_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                partPreparedStatementMapper.mapPreparedStatement(ps, parts.get(i));

            }

            @Override
            public int getBatchSize() {
                return parts.size();
            }

        });
    }

    @Override
    public List<Part> getAllByPartStorageName(Set<String> partStorageNameSet) {
        String inSql = String.join(",", Collections.nCopies(partStorageNameSet.size(), "?"));


        return jdbcTemplate.query(String.format(PartQuery.GET_PARTS_BY_STORAGE_NAME, inSql), partStorageNameSet.toArray(), new PartRowMapper());

    }
}
