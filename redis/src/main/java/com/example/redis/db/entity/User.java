package com.example.redis.db.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private Integer age;
    private String email;
    private Integer status;
}
