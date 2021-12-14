package com.example.oauth2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * oauth2授权服务器配置
 * */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;

    // token和redis是一样的配置
    @Resource
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    // redis配置
//    @Resource
//    @Qualifier("redisTokenStore")
//    private TokenStore tokenStore;


    /**
     * 使用密码模式配置
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 配置JWT内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // 配置存储令牌策略
                .tokenStore(tokenStore)
                // accessToken和jwtToken转换
                .accessTokenConverter(jwtAccessTokenConverter)
                // 放入enhancerChain
                .tokenEnhancer(enhancerChain);
    }

    /**
     * 总配置
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 授权服务器配置，存储到内存中，正常情况是去授权服务器注册出来
        clients.inMemory()
                // Client-id
                .withClient("admin")
                // secret
                .secret(passwordEncoder.encode("112233"))
                // 配置token有效期
                .accessTokenValiditySeconds(3600)
                // 配置refresh_token有效期
                .refreshTokenValiditySeconds(36000)
                // 授权成功后跳转
                .redirectUris("https://www.baidu.com")
                // 授权范围
                .scopes("all")
                // 授权码类型
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }
}
