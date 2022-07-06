package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.part.Part;
import com.corphelper.mailparser.repository.PartRepository;
import com.corphelper.mailparser.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.corphelper.mailparser.repository.query.PartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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


    @Override
    public Optional<UUID> getIdByCodeAndBrand(String code, String brandName) {

        try{

            return jdbcTemplate.queryForObject(
                    String.format(PartQuery.GET_PART_BY_CODE_AND_STORAGE_NAME, code, brandName), Optional.class);

        }catch (EmptyResultDataAccessException exception){

            return Optional.empty();
        }
    }
}
