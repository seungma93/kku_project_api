package com.jls.kku_final_api;

import java.util.HashMap;
import java.util.Map;

public class Role {
    public final static int ADMIN = 1;
    public final static int NUSER = 2;
    public final static int VUSER = 3;

    public static Map getRole(int role) {
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
