package com.example.springboot.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.db.entity.User;


public interface UserDao extends BaseMapper<User> {
    User findUserById(int id);
}
