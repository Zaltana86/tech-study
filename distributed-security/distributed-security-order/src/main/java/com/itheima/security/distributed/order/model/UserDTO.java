package com.itheima.security.distributed.order.model;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserDTO {

    private String uid;
    private String userName;
    private String password;
    private String fullname;
}
