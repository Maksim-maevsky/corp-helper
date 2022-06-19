package com.corphelper.mailparser.repository;

import com.corphelper.mailparser.entity.Part;
import com.corphelper.mailparser.repository.query.PartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@RequiredArgsConstructor
@Repository
public class PartRepositoryImpl {


    private final JdbcTemplate jdbcTemplate;


    public void delete(short storagePartId) {

        Object[] args = new Object[]{storagePartId};
        jdbcTemplate.update(PartQuery.DELETE_PARTS_BY_STORAGE_ID, args);

    }

    public int save(Part part) {

        return jdbcTemplate.update(PartQuery.SAVE_PART_QUERY, part.getId(), part.getCode(), part.getDescription(), part.getCreateDate(),
                part.getBrand(), part.getCount(), part.getPartStorageId());
    }
}
