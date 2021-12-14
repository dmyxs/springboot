package com.dmyxs.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 基于InMemoryUserDetailsManager创建用户信息的类
 * */
//@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
                User.withUsername("admin")
                    .password(passwordEncoder().encode("123456"))
                    .roles("admin", "normal").build());

        manager.createUser(
                User.withUsername("zs")
                    .password(passwordEncoder().encode("123456"))
                    .roles("normal").build());

        return manager;
    }
}
