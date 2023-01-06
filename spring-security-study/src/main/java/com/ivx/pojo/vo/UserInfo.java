package com.ivx.pojo.vo;


import com.ivx.pojo.entity.PermissionEntity;
import lombok.Data;

import java.util.List;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @since 2022/8/24 11:30
 * @apiNote 
*/
@Data
public class UserInfo {
    private String uid;
    private String userName;
    private String password;
    private String fullName;
    private String roleName;
    private List<PermissionEntity> permissionList;
}
