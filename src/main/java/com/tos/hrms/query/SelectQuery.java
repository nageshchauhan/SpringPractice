package com.tos.hrms.query;

public class SelectQuery {

    public static final String VERIFY_CRED="SELECT count(user_id) from user_cred where user_name=? and password=?;";

    public static final String GET_USER_ID_BY_USERNAME="SELECT uc.user_id, u.first_name, u.last_name from user_cred uc, users u where u.user_id=uc.user_id AND uc.user_name=?;";

    public static final String GET_USER_DETAIL="SELECT user_id, first_name, last_name from users where id=?;";
    
    public static final String HRMS_USER_QUERY = "select user_id, email, password, first_name, last_name from users where ";
}
