package com.tos.hrms.core;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tos.hrms.bean.HrmsUser;
import com.tos.hrms.dao.UserDao;

@Component
public class HrmsUserDetailsService implements UserDetailsService{

    private final UserDao userDao;

    @Autowired
    public HrmsUserDetailsService(UserDao userDao) {
        if (userDao == null) {
            throw new IllegalArgumentException("userDao cannot be null");
        }
        this.userDao = userDao;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HrmsUser user = userDao.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        Collection<? extends GrantedAuthority> authorities = UserAuthorityUtil.createAuthorities(user);
        //return new User(user.getEmail(), user.getPassword(), authorities);
        return new HrmsUserDetails(user);
    }
}

final class HrmsUserDetails extends HrmsUser implements UserDetails { 
    /**
     * 
     */
    private static final long serialVersionUID = -1935801435798245895L;
    HrmsUserDetails(HrmsUser user) { 
        setId(user.getId()); 
        setEmail(user.getEmail()); 
        setFirstName(user.getFirstName()); 
        setLastName(user.getLastName()); 
        setPassword(user.getPassword()); 
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return UserAuthorityUtil.createAuthorities(this); 
    } 

    public String getUsername() { 
        return getEmail(); 
    } 
    public boolean isAccountNonExpired() { return true; } 
    public boolean isAccountNonLocked() { return true; } 
    public boolean isCredentialsNonExpired() { return true; } 
    public boolean isEnabled() { return true; }
}