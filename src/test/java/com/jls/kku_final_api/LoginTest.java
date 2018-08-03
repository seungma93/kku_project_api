package com.jls.kku_final_api;

import com.jls.kku_final_api.Controller.LoginController;
import com.jls.kku_final_api.Repository.LoginRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    @Autowired
    private LoginRepository repository;

    @Test
    public void loginTest() throws IOException {
        LoginController loginController = new LoginController();
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("client_id", "nuser");
        userInfo.put("client_secret", "kku_final");
        userInfo.put("u_id", "joe");
        userInfo.put("u_pw", "1234");
        loginController.login(userInfo);
    }
}
