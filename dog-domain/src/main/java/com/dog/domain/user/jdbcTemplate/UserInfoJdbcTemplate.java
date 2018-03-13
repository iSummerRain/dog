package com.dog.domain.user.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserInfoJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int create(String id, String name, Integer age, String address) {
       return jdbcTemplate.update("insert into users(id, name, age, address) values(?, ?, ?, ?)", id, name, age, address);
    }

    public void deleteByName(String name) {
        jdbcTemplate.update("delete from users where name = ?", name);
    }

    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from users", Integer.class);
    }

    public void deleteAllUsers() {
        jdbcTemplate.update("delete from users");
    }
}
