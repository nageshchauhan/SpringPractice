package com.tos.hrms.service;

import com.tos.hrms.bean.HrmsUser;

public interface UserContext {

    /**
     * Gets the currently logged in {@link HrmsUser} or null if there is no authenticated user.
     *
     * @return
     */
    HrmsUser getCurrentUser();

    /**
     * Sets the currently logged in {@link HrmsUser}.
     * @param user the logged in {@link HrmsUser}. Cannot be null.
     * @throws IllegalArgumentException if the {@link HrmsUser} is null.
     */
    void setCurrentUser(HrmsUser user);
}
