package com.tos.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.hrms.bean.HrmsUser;
import com.tos.hrms.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        
        if (userDao == null) {
            throw new IllegalArgumentException("userDao cannot be null");
        }
        
        this.userDao = userDao;
    }

    @Override
    public HrmsUser getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public HrmsUser findUserByEmailId(String emailId) {
        return userDao.findUserByEmail(emailId);
    }

}
