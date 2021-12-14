package com.dmyxs.oauth2;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    public void createToken() {
        // 当前系统时间
        Long now = System.currentTimeMillis();
        // 失效时间: 2分钟
        Long exp = now + 120 * 1000;

        // 配置对象
        JwtBuilder jwtBuilder = Jwts.builder()
                // "jti": "8888"
                .setId("8888")
                // "sub": "Rose"
                .setSubject("Rose")
                // "ita": "xxxx"
                .setIssuedAt(new Date())
                // exp：过期时间
                .setExpiration(new Date(exp))
                // 自定义信息
                .claim("role", "admin")
                // 自定义信息，使用map
                //.addClaims(map)
                // salt
                .signWith(SignatureAlgorithm.HS512, "dmyxs");

        // 生成令牌
        String token = jwtBuilder.compact();
        String[] split = token.split("\\.");
        System.out.println("token: " + token);
        System.out.println("token_header: " + Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println("token_body: " + Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println("token_sign: " + Base64Codec.BASE64.decodeToString(split[2]));
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYzOTE4Nzg0MSwiZXhwIjoxNjM5MTg3OTYxfQ.BNe0g89J8GNK7fM3i3bEGly3C8ZDgR8aEyYYp9Wct4DM_PYwsfYpVPw74xvFK9PJB0nHk7xYi4FBiid7DJ7RUA";

        // 解析对象
        Claims info = Jwts.parser().setSigningKey("dmyxs")
                      .parseClaimsJws(token)
                      .getBody();

        System.out.println("info: " + info);
        System.out.println("Id: " + info.getId());
        System.out.println("Subject: " + info.getSubject());
        System.out.println("IssuedAt: " + info.getIssuedAt());
        System.out.println("自定义role: " + info.get("role"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间: " + simpleDateFormat.format(info.getIssuedAt()));
        System.out.println("过期时间: " + simpleDateFormat.format(info.getExpiration()));
        System.out.println("当前时间: " + simpleDateFormat.format((new Date())));
    }
}
