package com.dmyxs.security.config;

import com.dmyxs.security.dao.UserDao;
import com.dmyxs.security.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        UserEntity userEntity = userDao.selectUserByName(username);
        if(userEntity != null) {
            user = new User(username,userEntity.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_admin"));
        }
        return user;
    }
}
