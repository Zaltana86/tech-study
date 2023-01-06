package com.itcast.service.impl;

/*
Author: Inklo
Time: 2022/4/7 8:43
Target: 
*/

import com.itcast.dao.UserDao;
import com.itcast.dao.impl.UserDaoImpl;
import com.itcast.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public String findAllUsersString() {
        return userDao.findAllUsersString();
    }
}
