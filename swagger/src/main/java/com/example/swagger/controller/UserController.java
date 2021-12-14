package com.example.swagger.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api("user")
public class UserController {

    @GetMapping("")
    public String sayHi(){
        return "hello users";
    }
}
