package com.sahce.ufcg.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String password){
        return passwordEncoder.encode(password);
    }
}
