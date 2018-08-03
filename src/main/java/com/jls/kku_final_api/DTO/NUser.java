package com.jls.kku_final_api.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class NUser implements Serializable {
    @Id
    @Column(name = "u_id")
    private String id;

    @Column(name = "u_pw")
    private String pw;

    @Column(name = "u_nickname")
    private String nickname;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_profile_img")
    private String profileImg;

    @Column(name = "u_role")
    private String role;

    @Transient
    private String token;
}
