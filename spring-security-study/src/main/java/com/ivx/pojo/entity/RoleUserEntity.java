package com.ivx.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (RoleUser)实体类
 *
 * @author Skyler
 * @since 2022-08-24 11:19:48
 */

@Data  
public class RoleUserEntity implements Serializable {
    private static final long serialVersionUID = -16352785473506650L;
    
    private Integer roleUserId;
    
    private Integer userId;
    
    private Integer roleId;
}

