package com.example.socialmediaapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update2FAProperties(String id, String twofacode) {
        jdbcTemplate.update("update users set twofa_code=?, twofa_expire_time=? where id=?", new Object[] {
                twofacode, (System.currentTimeMillis()/1000) + 120, id
        });
    }
}
