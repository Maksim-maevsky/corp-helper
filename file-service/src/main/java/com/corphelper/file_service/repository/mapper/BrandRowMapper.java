package com.corphelper.file_service.repository.mapper;

import com.corphelper.file_service.entity.part.Brand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandRowMapper implements RowMapper<Brand> {


    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {

        Brand brand = new Brand();

        brand.setId(rs.getInt("id"));
        brand.setName(rs.getString("name"));

        return brand;
    }
}
