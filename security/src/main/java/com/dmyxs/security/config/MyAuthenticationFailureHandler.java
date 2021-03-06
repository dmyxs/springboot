package com.dmyxs.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录失败的自定义跳转
 * */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String url;

    public MyAuthenticationFailureHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
