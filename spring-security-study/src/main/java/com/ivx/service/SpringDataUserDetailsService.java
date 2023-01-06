package com.ivx.service;


import com.ivx.dao.IUserDao;
import com.ivx.pojo.vo.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote UserDetailsService 用户security校验
 * @since 2022/8/23 15:00
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Resource
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println(userName);
        UserInfo userInfo = userDao.getUserInfo(userName);
        System.out.println(userInfo);
        if (userInfo == null) {
            return null;  // 注入空
        }
        return User.withUsername(userInfo.getUserName())
                .password(userInfo.getPassword()).roles(userInfo.getRoleName())
                .authorities(userInfo.getPermissionList()).build();
    }
}
