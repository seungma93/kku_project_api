package com.jls.kku_final_api.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NUserLogin implements UserDetails {
    private String id;
    private String pw;
    private String nickname;
    private String email;
    private String profileImg;
    private List<String> roles;

    public NUserLogin(NUser user) {
        this.id = user.getId();
        this.pw = user.getPw();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.profileImg = user.getProfileImg();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
