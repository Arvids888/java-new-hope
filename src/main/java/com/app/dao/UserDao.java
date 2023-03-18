package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        RowMapper<User> rowMapper = (rs, rowNumber) -> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));

        return user;
    }

    public void storeUser(User user) {
        jdbcTemplate.update("INSERT INTO users (first_name, last_name, email, phone, password) VALUES (?, ?, ?, ?, 'web')",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
    }
}
