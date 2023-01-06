package com.ivx.controller;


import com.ivx.exception.MyException;
import com.ivx.util.MessageUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2022/12/23 17:17
 */
@RestController
@RequestMapping("user")
public class TestController {
    @GetMapping
    // http://localhost:8080/user?lang=en_US 通过lang指定语言
    public String getInfo() {
        throw new MyException("user.error");
    }
}
