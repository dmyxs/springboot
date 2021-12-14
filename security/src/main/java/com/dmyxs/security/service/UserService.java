package com.dmyxs.security.service;

import com.dmyxs.security.entity.UserEntity;

public interface UserService {
    UserEntity selectUserById(int userId);
}
