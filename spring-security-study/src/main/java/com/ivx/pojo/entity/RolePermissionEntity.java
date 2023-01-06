package com.ivx.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (RolePermission)实体类
 *
 * @author Skyler
 * @since 2022-08-24 11:19:48
 */

@Data  
public class RolePermissionEntity implements Serializable {
    private static final long serialVersionUID = -10806666330210598L;
    
    private Integer rolePermissionId;
    
    private Integer roleId;
    
    private Integer permissionId;
}

