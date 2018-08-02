package com.jls.kku_final_api;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HttpTest {
    @Test
    public void tokenTest() throws IOException {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("root", "12345".toCharArray());
            }
        });

        ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        String tokenUrl = "http://localhost:8080/api/oauth/token";
        tokenUrl += "?grant_type=password";
        tokenUrl += "&username=joe";
        tokenUrl += "&password=1234";

        response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);
        /*BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        while ((tmp = buffer.readLine()) != null) {
            sb.append(tmp);
        }

        System.out.println(sb.toString());*/
    }
}
