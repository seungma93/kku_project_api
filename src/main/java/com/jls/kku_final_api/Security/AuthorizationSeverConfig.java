package com.jls.kku_final_api.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import java.security.KeyPair;
import java.util.logging.Logger;

@Configuration
@EnableAuthorizationServer
public class AuthorizationSeverConfig extends AuthorizationServerConfigurerAdapter {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${access_token.validity_period:36000}")
    int accessTokenValiditySeconds = 3600;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        ClassPathResource pathResource = new ClassPathResource("server.jks");
        KeyPair keyPair = new KeyStoreKeyFactory(pathResource, "qweqwe".toCharArray())
                .getKeyPair("server_private", "zaqwsx".toCharArray());
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    @Primary
    public JdbcClientDetailsService JdbcClientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //oauth_client_details 테이블에 등록된 사용자로 조회한다.
        clients.withClientDetails(clientDetailsService);
        /*clients.inMemory()
                .withClient("client1")
                .authorizedGrantTypes("authorization_code", "password", "implicit", "refresh_token")
                .authorities("ROLE_YOUR_CLIENT")
                .scopes("read", "write")
                //.resourceIds(resourceId)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .secret("client1pwd");*/
    }

}
