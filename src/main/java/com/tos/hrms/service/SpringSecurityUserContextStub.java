package com.tos.hrms.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tos.hrms.bean.HrmsUser;
import com.tos.hrms.core.UserAuthorityUtil;

@Component
public class SpringSecurityUserContextStub implements UserContext {

    private final UserService userService;
    
    @Autowired
    public SpringSecurityUserContextStub(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.userService = userService;
    }

    //@Override
    public HrmsUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext(); 
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
          return null;
        }
        //String email = authentication.getName();
        return (HrmsUser) authentication.getPrincipal(); 
       /* if (email == null) {
            return null;
        }
        HrmsUser hrmsUser = userService.findUserByEmailId(email);
        if (hrmsUser == null) {
            throw new IllegalStateException(
                    "Spring Security is not in synch with CalendarUsers. Could not find user with email " + email);
        }
        return hrmsUser;*/
    }

    //@Override
    public void setCurrentUser(HrmsUser user) {
        Collection authorities = UserAuthorityUtil.createAuthorities(user); 
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),authorities); 
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
