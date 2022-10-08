package com.sswl.mapper;

import com.sswl.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: chen
 * @Date: 2022/1/12 19:00
 * @Desc:
 */
public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        return new User(id, name, age);
    }
}
