package com.dmyxs.security.controller;

import com.dmyxs.security.entity.UserEntity;
import com.dmyxs.security.service.Imlp.UserServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @Secured("ROLE_adminX")
    @GetMapping("/hello")
    public String sayHi() {
        return "hello security";
    }

    @RequestMapping("toMain")
    public String toMain(){
        return "redirect:main.html";
    }

    @RequestMapping("toError")
    public String toError(){
        return "redirect:error.html";
    }

    @GetMapping("/user/{userId}")
    public UserEntity selectUserById(@PathVariable int userId) {
        UserEntity userEntity = userServiceImpl.selectUserById(userId);
        System.out.println("user: " + userEntity);
        return userEntity;
    }

    @RequestMapping("demo")
    public String demo(){
        return "demo";
    }


    @RequestMapping("/anyRole")
    @PreAuthorize("hasAnyRole('admin', 'normal')")
    public String anyRole(){
        return "any role";
    }

    @RequestMapping("/onlyAdmin")
    @PreAuthorize("hasAnyRole('admin')")
    public String onlyAdmin(){
        return "only Admin";
    }
}
