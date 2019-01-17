package com.tos.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.hrms.bean.User;
import com.tos.hrms.dao.LoginDao;
import com.tos.hrms.exception.AuthenticationFailure;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    public int checkUser(String username, String password) throws AuthenticationFailure {
        int returnValue =loginDao.verfiyCreadential(username, password);
        if(returnValue==0){
            // login failure
            throw new AuthenticationFailure("Invalid username and password");
        }

        return returnValue;
    }

    public User getUserDtlsByUsername(String username) {
        return loginDao.getUserDetailByUsername(username);
    }

}
