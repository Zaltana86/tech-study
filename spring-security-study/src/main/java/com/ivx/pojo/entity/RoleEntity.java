package com.ivx.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (Role)实体类
 *
 * @author Skyler
 * @since 2022-08-24 11:19:48
 */

@Data  
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 251694103532176816L;
    
    private Integer roleId;
    
    private String roleName;
    
    private String description;
}

