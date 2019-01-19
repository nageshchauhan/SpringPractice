package com.tos.hrms.service;

import com.tos.hrms.bean.User;
import com.tos.hrms.exception.AuthenticationFailure;

public interface LoginService {

    int checkUser(String username, String password) throws AuthenticationFailure;

    User getUserDtlsByUsername(String username);
}
