package com.jls.kku_final_api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Constants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.security.*;
import java.util.logging.Logger;

@Configuration
@EnableAuthorizationServer
public class AuthorizationSeverConfig extends AuthorizationServerConfigurerAdapter {

    public AuthorizationSeverConfig() {
        System.out.println("EnableAuthorizationServer");
    }

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${access_token.validity_period:3600}")
    int accessTokenValiditySeconds = 3600;

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String publicKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        System.out.println("EnableAuthorizationServer tokenStore");
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        System.out.println("EnableAuthorizationServer tokenStore");
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        ClassPathResource pathResource = new ClassPathResource("server.jks");
        KeyPair keyPair = new KeyStoreKeyFactory(pathResource, "qweqwe".toCharArray())
                .getKeyPair("server_private", "zaqwsx".toCharArray());
        converter.setKeyPair(keyPair);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService() {
        System.out.println("EnableAuthorizationServer tokenService");
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        System.out.println("EnableAuthorizationServer endpoints");
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    @Primary
    public JdbcClientDetailsService JdbcClientDetailsService(DataSource dataSource) {
        System.out.println("EnableAuthorizationServer JdbcClientDetailsService");
        return new JdbcClientDetailsService(dataSource);
    }

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("EnableAuthorizationServer ClientDetailsServiceConfigurer");
        //oauth_client_details 테이블에 등록된 사용자로 조회한다.
        clients.withClientDetails(clientDetailsService);
    }

}
