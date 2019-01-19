package com.tos.hrms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tos.hrms.bean.User;
import com.tos.hrms.dao.mapper.UsersMapper;
import com.tos.hrms.query.SelectQuery;

@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int verfiyCreadential(String username, String password) {
        return jdbcTemplate.queryForObject(SelectQuery.VERIFY_CRED, new Object[]{username, password}, Integer.class);
    }

    public User getUserDetailByUsername(String username) {
        User u = jdbcTemplate.queryForObject(SelectQuery.GET_USER_ID_BY_USERNAME, new Object[]{username}, new UsersMapper());
        return u;
    }

}
