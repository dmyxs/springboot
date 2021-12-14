package com.example.redis.controller;

import com.example.redis.db.entity.User;
import com.example.redis.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/init")
    public void init(){
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            User user = new User();
            String temp = "un" + i;
            user.setUsername(temp);
            user.setPassword(temp);
            int sex = random.nextInt(2);
            user.setSex(sex);
            int age = random.nextInt(43) + 18;
            user.setAge(age);
            user.setEmail(user.getUsername() + "@163.com");
            userService.insertUser(user);
        }
    }

    @GetMapping("/{userId}")
    public User selectUser(@PathVariable int userId){
        ((UserController) AopContext.currentProxy()).saveRedis(userId);
        return userService.selectUserById(userId);
    }

    @Cacheable(value = "merchandise", key = "#id")
    public String saveRedis(Integer id){
        return "坎里·德·赫列娜";
    }

    @PostMapping("")
    public String updateUser(@RequestBody User obj){
        User user = new User();
        BeanUtils.copyProperties(obj, user);
        userService.updateUser(user);
        return "ok";
    }

    @PutMapping("/{userId}")
    public int deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }
}
