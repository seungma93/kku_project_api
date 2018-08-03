package com.jls.kku_final_api.Controller;

import com.jls.kku_final_api.DTO.NUser;
import com.jls.kku_final_api.Repository.LoginRepository;
import com.jls.kku_final_api.Role;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginRepository repository;

    @GetMapping("findId")
    public ResponseEntity<NUser> findId(String id) throws RuntimeException {
        Optional<NUser> nUser = repository.findById(id);
        if (!nUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nUser.get(), HttpStatus.OK);
    }

    @GetMapping("login")
    public ResponseEntity<NUser> login(String id, String pw, String role) throws RuntimeException, IOException {
        Optional<NUser> user = repository.findByIdAndPw(id, pw);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String token = getToken(id, pw, role);
        user.get().setToken(token);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    private String getToken(String userId, String userPw, String role) throws IOException {
        Map roleInfo = Role.getRole(role);
        String clientId = (String) roleInfo.get("client_id");
        String clientSecret = (String) roleInfo.get("client_secret");
        String urlStr = "http://127.0.0.1:8080/api/oauth/token?grant_type=password" +
                "&username=" + userId +
                "&password=" + userPw;

        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        String auth = clientId + ":" + clientSecret;
        byte[] authByte = Base64.encodeBase64(auth.getBytes());
        String authInfo = new String(authByte);
        urlConnection.setRequestProperty("Authorization", "Basic " + authInfo);
        InputStream inputStream = httpURLConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        StringBuilder tokenBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            tokenBuilder.append(scanner.nextLine());
        }
        String token = tokenBuilder.toString().split("\"")[3];
        return token;
    }
}
