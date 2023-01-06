package com.ivx.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author Skyler
 * @since 2022-08-24 11:19:48
 */
@TableName("user")
@Data  
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 713669860847363651L;
    /**
     * 主键
     */
    @TableId
    private Integer uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String fullName;
}

