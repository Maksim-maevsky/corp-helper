package com.corphelper.mailparser.repository.impl;

import com.corphelper.mailparser.entity.part.Brand;
import com.corphelper.mailparser.repository.BrandRepository;
import com.corphelper.mailparser.repository.mapper.BrandRowMapper;
import com.corphelper.mailparser.repository.query.BrandQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Repository
public class BrandRepositoryImpl implements BrandRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Brand> getByName(String name) {


        try {

             return Optional.of(jdbcTemplate.queryForObject(String.format(BrandQuery.GET_BY_NAME), new BrandRowMapper(), name));

        } catch (EmptyResultDataAccessException exception) {

            return Optional.empty();
        }
    }
}
