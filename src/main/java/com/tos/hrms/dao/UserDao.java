package com.tos.hrms.dao;

import java.util.List;

import com.tos.hrms.bean.HrmsUser;


public interface UserDao {

    HrmsUser getUser(int id);
    
    HrmsUser findUserByEmail(String email);
    
    int createUser(HrmsUser user);
    
    List<HrmsUser> findUsersByEmail(String partialEmail);
}
