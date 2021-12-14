package com.example.springboot.db.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
