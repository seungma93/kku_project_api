package com.jls.kku_final_api.Repository;

import com.jls.kku_final_api.DTO.NUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface LoginRepository extends JpaRepository<NUser, String> {
    @Override
    List<NUser> findAll();

    @Override
    Optional<NUser> findById(@Param("u_id") String u_id);
}
