package com.ivx.controller;


import com.ivx.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: Skyler
 * Email: kl142857h@163.com
 * Time: 2022/8/3 15:07
 * Target:
 */
@RestController
@RequestMapping("user")
public class UserController {
    // @PostMapping("login")
    // public String login(@RequestBody User user) {
    //     return "登陆成功";
    // }
    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('p1')")  // 这个表达式可以取容器对象
    @GetMapping(value = "r/r1", produces = "text/plain;charset=UTF-8")
    public String r1() {
        userService.testAuthMethod();
        return getUserName() + "访问资源r1";
    }


    @PreAuthorize("hasAuthority('p2')")
    @GetMapping(value = "r/r2", produces = "text/plain;charset=UTF-8")
    public String r2() {
        return getUserName() + "访问资源r2";
    }

    @PostMapping(value = "/login_success", produces = "text/plain;charset=UTF-8")  // 这个编码必须是大写
    public String success() {
        return getUserName() + "登陆成功！";
    }

    private String getUserName() {
        String userName;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //获取当前线程的认证上下文对象
        Object principal = authentication.getPrincipal();// 获取到认证信息
        if (principal == null) {
            return "匿名用户";
        }
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            // 这个user是springsecurity提供的user
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            userName = user.getUsername();
            return userName;
        }
        return principal.toString();
    }
}
