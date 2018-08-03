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

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        String tokenUrl = "http://root:12345@localhost:8080/api/oauth/token";
        tokenUrl += "?grant_type=password";
        tokenUrl += "&username=joe";
        tokenUrl += "&password=1234";

        response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, String.class);
    }

    @Test
    public void httpTest2() throws IOException {
        URL url = new URL("http://127.0.0.1:8080/api/oauth/token?grant_type=password&username=joe&password=1234");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        String auth = "nuser:kku_final";
        byte[] authByte = Base64.encodeBase64(auth.getBytes());
        String authInfo = new String(authByte);
        urlConnection.setRequestProperty("Authorization", "Basic " + authInfo);
        InputStream inputStream = httpURLConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}
