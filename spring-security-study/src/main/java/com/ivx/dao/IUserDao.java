package com.ivx.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ivx.pojo.entity.UserEntity;
import com.ivx.pojo.vo.UserInfo;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/8/23 16:42
 * @apiNote 
*/
public interface IUserDao extends BaseMapper<UserEntity> {
    UserInfo getUserInfo(String userName);
}
