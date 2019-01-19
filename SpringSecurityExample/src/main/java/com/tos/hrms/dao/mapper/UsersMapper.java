package com.tos.hrms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tos.hrms.bean.User;

public class UsersMapper implements RowMapper<User>{

	public User mapRow(ResultSet rs, int index) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("user_id"));
		u.setFirstName(rs.getString("first_name"));
		u.setLastName(rs.getString("last_name"));
		return u;
	}

}
