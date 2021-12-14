package com.dmyxs.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Long id;
    private String username;
    private String password;
    private String role;
}
