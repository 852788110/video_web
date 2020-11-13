package com.liu.dto;

import com.liu.mbg.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
*   SpringSecurity需要的用户详情
* */
public class AdminUserDetails implements UserDetails {

    private User userAdmin;

    public AdminUserDetails(User userAdmin) {
        this.userAdmin = userAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userAdmin.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
