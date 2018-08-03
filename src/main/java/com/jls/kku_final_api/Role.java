package com.jls.kku_final_api;

import java.util.HashMap;
import java.util.Map;

public class Role {
    public final static String ADMIN = "admin";
    public final static String NUSER = "nuser";
    public final static String VUSER = "vuser";

    public static Map getRole(String role) {
        Map<String, String> userInfo = new HashMap<>();
        switch (role) {
            case ADMIN:
                userInfo.put("client_id", "admin");
                userInfo.put("client_secret", "kku_final");
                return userInfo;

            case NUSER:
                userInfo.put("client_id", "nuser");
                userInfo.put("client_secret", "kku_final");
                return userInfo;

            case VUSER:
                userInfo.put("client_id", "vuser");
                userInfo.put("client_secret", "kku_final");
                return userInfo;
        }
        return null;
    }
}
