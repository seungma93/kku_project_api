package com.jls.kku_final_api.Controller;

import com.jls.kku_final_api.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@EnableAutoConfiguration
public class RestController {
    @Autowired
    private Repository repository;

    @GetMapping("/test")
    @ResponseBody
    public Map getDatas() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", repository.getDatas());
        return map;
    }
}
