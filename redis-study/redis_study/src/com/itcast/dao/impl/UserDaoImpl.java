package com.itcast.dao.impl;

/*
Author: Inklo
Time: 2022/4/7 8:26
Target: 
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.dao.UserDao;
import com.itcast.entity.User;
import com.itcast.util.JdbcUtilsDataSource;
import com.itcast.util.RedisPoolUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate template  = new JdbcTemplate(JdbcUtilsDataSource.getDataSource());
    @Override
    public List<User> findAllUsers() {
        String sql = "select * from users";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class));
    }
    // 如果使用缓存，每个方法使用对象的缓存更改，使用增删改查需要对redis数据清空
    @Override
    public String findAllUsersString() {
        Jedis jedis = RedisPoolUtil.getJedis();
        // 先从redis缓存里面找，如果没找到把数据加入到redis缓存中
        String users = jedis.get("users");
        if (users == null || users.length() == 0) {
            List<User> userList = findAllUsers();
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(userList);
                // 存入数据库中
                jedis.set("users",json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        jedis.close();
        return users;
    }
}
