package com.example.mininventory.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_ALSO_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
