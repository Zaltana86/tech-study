package com.ivx.controller;


import com.ivx.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/22 16:03
 */
@RestController
@RequestMapping("users")
public class TestController {
    @GetMapping("hello")
    public ResponseEntity<User> info() throws Exception {
        return new ResponseEntity<>(new User("skyler", "123.com"), HttpStatus.OK);
    }
}
