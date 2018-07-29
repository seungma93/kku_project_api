package com.jls.kku_final_api.Security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class SecurityConfigTest {

    @Test
    public void passwordEncoder() {
        BCryptPasswordEncoder bcr = new BCryptPasswordEncoder();
        String result = bcr.encode("12345");
        System.out.println("암호 === " + result);
    }
}