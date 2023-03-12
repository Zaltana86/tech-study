package com.itheima.security.distributed.order.controller;

import cn.hutool.json.JSONUtil;
import com.itheima.security.distributed.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")//拥有p1权限方可访问此url
    public String r1() {
        //获取用户身份信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = JSONUtil.parseObj( authentication.getPrincipal()).toBean(UserDTO.class);
        return userDTO.getUserName()+ "访问资源1";
    }

}
