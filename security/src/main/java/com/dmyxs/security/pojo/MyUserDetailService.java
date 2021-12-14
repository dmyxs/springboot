package com.dmyxs.security.pojo;

import com.dmyxs.security.dao.UserDao;
import com.dmyxs.security.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过数据库的方式获取用户角色，再判断权限的方法
 * */
//@Component("MyUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        UserEntity userEntity = null;
        if (username != null) {
            userEntity = userDao.selectUserByName(username);
            System.out.println("userEntity: " + userEntity);
            if(userEntity != null) {
                // new User的规定写法：第三参数：角色权限集合
                List<GrantedAuthority> authorityList = new ArrayList<>();

                // 设置角色到权限集合中
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole());
                authorityList.add(grantedAuthority);

                // 创建user对象
                user = new User(userEntity.getUsername(),userEntity.getPassword(),authorityList);
            }
        }

        return user;
    }
}
