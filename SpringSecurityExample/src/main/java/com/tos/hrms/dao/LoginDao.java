package com.tos.hrms.dao;

import com.tos.hrms.bean.User;

public interface LoginDao {

	int verfiyCreadential(String username, String password);

	User getUserDetailByUsername(String username);
}
