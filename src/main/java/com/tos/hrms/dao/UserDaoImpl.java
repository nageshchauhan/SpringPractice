package com.tos.hrms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tos.hrms.bean.HrmsUser;
import com.tos.hrms.query.SelectQuery;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcOperations jdbcOperations;
    
    @Autowired
    public UserDaoImpl(JdbcOperations jdbcOperations) {
        if (jdbcOperations == null) {
            throw new IllegalArgumentException("jdbcOperations cannot be null");
        }
        this.jdbcOperations = jdbcOperations;
    }
    
    
    @Override
    public HrmsUser getUser(int id) {
        return jdbcOperations.queryForObject(SelectQuery.HRMS_USER_QUERY+"id = ?", new HrmsUserRowMapper(), new Object[]{id});
    }

    @Override
    public HrmsUser findUserByEmail(String email) {
        return jdbcOperations.queryForObject(SelectQuery.HRMS_USER_QUERY+"email = ?", new HrmsUserRowMapper(), new Object[]{email});
    }

    @Override
    public int createUser(HrmsUser user) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HrmsUser> findUsersByEmail(String partialEmail) {
        if (partialEmail == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if ("".equals(partialEmail)) {
            throw new IllegalArgumentException("email cannot be empty string");
        }
        return jdbcOperations.query(SelectQuery.HRMS_USER_QUERY + "email like ? order by id", new HrmsUserRowMapper(), "%"+partialEmail + "%");
    }

}
