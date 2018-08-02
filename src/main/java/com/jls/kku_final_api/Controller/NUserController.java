package com.jls.kku_final_api.Controller;

import com.jls.kku_final_api.DTO.NUser;
import com.jls.kku_final_api.DTO.Response;
import com.jls.kku_final_api.Repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nuser")
public class NUserController {

    @Autowired
    private NUserRepository repository;

    //유저 조회
    @GetMapping("/user")
    public Response<List<NUser>> findByUser() throws Exception {
        Response<List<NUser>> response = new Response<>();
        List<NUser> users = repository.findAll();
        response.setResponse(users);

        if (users.size() == 0) {
            response.setSuccess(false);
        }
        return response;
    }

}
