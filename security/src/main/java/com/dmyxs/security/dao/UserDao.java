package com.dmyxs.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmyxs.security.entity.UserEntity;

public interface UserDao extends BaseMapper<UserEntity> {
    UserEntity selectUserByName(String username);
}
