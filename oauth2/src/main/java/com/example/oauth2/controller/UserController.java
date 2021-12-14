package com.example.oauth2.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication,HttpServletRequest httpServletRequest){
        String head = httpServletRequest.getHeader("Authorization");
        String token = head.substring(head.indexOf("bearer") + 7);

        return Jwts.parser()
                .setSigningKey("dmyxs".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
