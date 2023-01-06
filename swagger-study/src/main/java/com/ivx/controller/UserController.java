package com.ivx.controller;


import com.ivx.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/8/22 11:02
 */
@RestController
@Api(tags = "用户")
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    @ApiOperation(value = "用户登陆", notes = "使用用户名和密码登陆")
    public User login(@RequestBody User user) {
        if (!"root".equals(user.getUserName()) && "123.com".equals(user.getPassword())) {
            return null;
        }
        return user;
    }

    // 普通url参数
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",//参数名字
                    value = "用户名",//参数的描述
                    required = true,//是否必须传入
                    //paramType定义参数传递类型：有path,query,body,form,header
                    paramType = "query"
            )
            ,
            @ApiImplicitParam(name = "password",//参数名字
                    value = "密码",//参数的描述
                    required = true,//是否必须传入
                    paramType = "query"
            )
    })
    @ApiResponse(code = 200, message = "操作成功")  // 只能通过响应码来说明数据
    @ApiOperation(value = "用户登陆1", notes = "使用用户名和密码登陆")
    @PostMapping("login1")
    public User login1(String userName, String password) {
        if (!"root".equals(userName) && "123.com".equals(password)) {
            return null;
        }
        return new User(userName, password);
    }

    // 路径参数
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",//参数名字
                    value = "用户名",//参数的描述
                    required = true,//是否必须传入
                    paramType = "path"
            )
    })
    @GetMapping("{id}")
    public String getUserById(@PathVariable Integer id) {
        return String.valueOf(id);
    }
}
