package com.example.springboot.service.interfaces;

import com.example.springboot.db.entity.User;

public interface UserService {
//    List<User> selectUser();
    User selectUserById(int userId);
}
