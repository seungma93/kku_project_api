package com.jls.kku_final_api.Controller;

import com.jls.kku_final_api.DTO.NUser;
import com.jls.kku_final_api.DTO.Response;
import com.jls.kku_final_api.Repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private NUserRepository repository;

    @GetMapping("findId")
    public ResponseEntity<NUser> login(String id) throws RuntimeException {
        Response<NUser> response = new Response<>();
        Optional<NUser> nUser = repository.findById(id);
        if (!nUser.isPresent()) {
            //throw new NotFoundException("id not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nUser.get(), HttpStatus.OK);
    }
}
