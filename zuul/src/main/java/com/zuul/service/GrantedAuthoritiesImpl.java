package com.zuul.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class GrantedAuthoritiesImpl implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ADMIN";
    }
}
