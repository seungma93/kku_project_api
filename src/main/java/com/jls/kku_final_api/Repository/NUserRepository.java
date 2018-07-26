package com.jls.kku_final_api.Repository;

import com.jls.kku_final_api.DTO.NUserVO;
import com.jls.kku_final_api.DTO.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NUserRepository extends JpaRepository<NUserVO, String> {
    @Override
    List<NUserVO> findAll();
}
