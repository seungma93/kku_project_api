package com.jls.kku_final_api.Repository;

import com.jls.kku_final_api.DTO.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@org.springframework.stereotype.Repository
public interface Repository {
    List<TestDTO> getDatas();
}
