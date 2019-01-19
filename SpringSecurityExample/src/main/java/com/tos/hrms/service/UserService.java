package com.tos.hrms.service;

import com.tos.hrms.bean.HrmsUser;


public interface UserService {

    HrmsUser getUser(int id);
    
    HrmsUser findUserByEmailId(String emailId);
}
