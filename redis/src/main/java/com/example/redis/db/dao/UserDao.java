package com.example.redis.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.redis.db.entity.User;

public interface UserDao extends BaseMapper<User> {
    User findUserById(int id);
}
