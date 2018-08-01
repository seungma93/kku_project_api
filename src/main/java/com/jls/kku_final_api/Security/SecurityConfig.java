package com.jls.kku_final_api.Security;

import com.jls.kku_final_api.Repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        System.out.println("SecurityConfig");
    }

    public SecurityConfig(boolean disableDefaults) {
        super(disableDefaults);
        System.out.println("SecurityConfig");
    }

    @Autowired
    private NUserDetailService userDetailService;

    /**
     * BCryptPasswordEncoder: bcrypt 해시 알고리즘을 이용하여 입력받은 데이터를 암호화한다
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("SecurityConfig passwordEncoder");
        return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfig configure(AuthenticationManagerBuilder auth)");
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * 데이터베이스 인증용 Provider
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        System.out.println("SecurityConfig authenticationProvider");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //패스워드를 암호활 경우 사용한다
        return authenticationProvider;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println("SecurityConfig authenticationManagerBean");
        return super.authenticationManagerBean();
    }


}
