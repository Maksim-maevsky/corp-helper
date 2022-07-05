package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.PartInfo;
import com.corphelper.mailparser.repository.PartInfoRepository;
import com.corphelper.mailparser.repository.mapper.PartRowMapper;
import com.corphelper.mailparser.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.PartInfoQuery;
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
public class PartInfoRepositoryImpl implements PartInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public void delete(short storagePartId) {

        Object[] args = new Object[]{storagePartId};
        jdbcTemplate.update(PartInfoQuery.DELETE_PARTS_BY_STORAGE_ID, args);

    }

    @Override
    public int save(PartInfo part) {

        return jdbcTemplate.update(PartInfoQuery.SAVE_PART_QUERY, part.getId(), part.getCount(), part.getPartStorageId(), part.getPartId(), part.getCreateDate());
    }

    @Override
    public int[] saveAll(List<PartInfo> parts) {

        return this.jdbcTemplate.batchUpdate(PartInfoQuery.SAVE_PART_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                partInfoPreparedStatementMapper.mapPreparedStatement(ps, parts.get(i));

            }

            @Override
            public int getBatchSize() {
                return parts.size();
            }

        });
    }

    @Override
    public List<PartInfo> getAllByPartStorageName(Set<String> partStorageNameSet) {
        String inSql = String.join(",", Collections.nCopies(partStorageNameSet.size(), "?"));


        return jdbcTemplate.query(String.format(PartInfoQuery.GET_PARTS_BY_STORAGE_NAME, inSql), partStorageNameSet.toArray(), new PartRowMapper());

    }
}
