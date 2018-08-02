package com.jls.kku_final_api.Security;

import com.jls.kku_final_api.DTO.NUser;
import com.jls.kku_final_api.DTO.NUserLogin;
import com.jls.kku_final_api.Repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class NUserDetailService implements UserDetailsService {

    @Autowired
    private NUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<NUser> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UsernameNotFoundException("id not found " + id);

        return createUser(user.get());
    }

    private NUserLogin createUser(NUser user) {
        NUserLogin userLogin = new NUserLogin(user);
        userLogin.setRoles(Arrays.asList("ROLE_USER"));
        return userLogin;
    }
}
