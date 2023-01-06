package com.itcast.dao;/*
Author: Inklo
Time: 2022/4/7 8:26
Target: 
*/

import com.itcast.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    String findAllUsersString();
}
