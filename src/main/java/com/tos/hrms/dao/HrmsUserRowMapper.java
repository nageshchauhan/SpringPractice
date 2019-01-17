package com.tos.hrms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tos.hrms.bean.HrmsUser;


public class HrmsUserRowMapper implements RowMapper<HrmsUser> {
    
    @Override
    public HrmsUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        HrmsUser user = new HrmsUser();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    }

}
