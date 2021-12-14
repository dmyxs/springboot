package com.example.springboot.controller;

import com.example.springboot.db.entity.User;
import com.example.springboot.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

//    @GetMapping("/{userId}")
//    public User selectUser(@PathVariable int userId){
//        System.out.println(userId);
//        return userService.selectUserById(userId);
//    }

//    @GetMapping("/{userId}")
//    public String selectUser(@PathVariable int userId, @RequestParam(value = "name", required = false) String name){
//        return "userId: " + userId + " name " + name;
//    }

//    @GetMapping("/{userId}")
//    public String selectUser(@PathVariable int userId,
//                             @RequestParam() Map<String, Object> map){
//        return "userId: " + userId + " name " + map.get("name") + " age " + map.get("age");
//    }


    @GetMapping("/{userId}")
    public User selectUser(@PathVariable int userId,
                           User user){
        return user;
    }

//    @PostMapping("")
//    public String insertUser(@RequestParam("name") String name,
//                          @RequestParam("age") Integer age){
//
//        return "name: " + name + "age: " + age;
//    }

//    @PostMapping("")
//    public String insertUser(@RequestBody() User user){
//        return "name: " + user.getName() + "age: " + user.getAge();
//    }@RequestHeader

    @PostMapping("")
    public String insertUser(@RequestHeader("Content-Type") String type){
        return "name: " + type;
    }
}
