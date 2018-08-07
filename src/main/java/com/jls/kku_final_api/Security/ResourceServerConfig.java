package com.jls.kku_final_api.Security;

import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Base64;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public ResourceServerConfig() {
        System.out.println("ResourceServerConfig");
    }

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String publicKey;

    @Bean
    public TokenStore tokenStore() {
        System.out.println("ResourceServerConfig tokenStore");
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        System.out.println("ResourceServerConfig accessTokenConverter");
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //converter.setVerifierKey(publicKey);
        converter.setSigningKey("hello");
        //converter.setSigningKey(publicKey);
        System.out.println(publicKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService() {
        System.out.println("ResourceServerConfig tokenService");
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println("ResourceServerConfig configure(HttpSecurity http)");
        super.configure(http);
    }*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        System.out.println("ResourceServerConfig configure(ResourceServerSecurityConfigurer resources)");
        System.out.println("ResourceServerConfig resourceId " + resourceId);
        resources.resourceId(resourceId);
    }
}
