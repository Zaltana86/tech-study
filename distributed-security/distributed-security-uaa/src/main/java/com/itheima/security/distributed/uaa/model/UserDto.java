package com.itheima.security.distributed.uaa.model;

import lombok.Data;

/**
 * @author Administrator
 * @version 1.0
 **/
@Data
public class UserDto {
    private String uid;
    private String userName;
    private String password;
    private String fullname;
}
