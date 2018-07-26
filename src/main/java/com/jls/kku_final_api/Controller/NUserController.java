package com.jls.kku_final_api.Controller;

import com.jls.kku_final_api.DTO.NUserVO;
import com.jls.kku_final_api.DTO.ResponseVO;
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
    public ResponseVO<List<NUserVO>> findByUser() throws Exception {
        ResponseVO<List<NUserVO>> response = new ResponseVO<>();
        List<NUserVO> users = repository.findAll();
        response.setResponse(users);

        if (users.size() == 0) {
            response.setSuccess(false);
            response.setCode(404);
        }
        return response;
    }
}
