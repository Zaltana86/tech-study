package com.ivx.controller;


import com.ivx.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/22 16:03
 */
@Api(tags = "用户相关接口",description = "用户接口")
@RestController
@RequestMapping("users")
public class TestController {
    @ApiOperation("用户信息")
    @GetMapping("hello")
    public User info() {
        // return new ResponseEntity<>(new Date(), HttpStatus.OK);
        return new User("skyler","123.com",19);
    }
}
