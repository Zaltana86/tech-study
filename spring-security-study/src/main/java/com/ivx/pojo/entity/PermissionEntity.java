package com.ivx.pojo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author Skyler
 * @since 2022-08-24 11:19:48
 */

@Data  
public class PermissionEntity implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = -60022760074432270L;
    
    private Integer permissionId;
    /**
     * 权限码
     */
    private String permissionCode;
    /**
     * 权限解释
     */
    private String description;
    
    private String url;

    @Override
    public String getAuthority() {
        return permissionCode;
    }
}

