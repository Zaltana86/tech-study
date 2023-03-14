package cn.itcast.user.web;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
// @RefreshScope
public class UserController implements UserClient {

    @Autowired
    private UserService userService;

    // @Value("${pattern.dateformat}")
    // private String dateformat;

    @Autowired
    private PatternProperties properties;

    @GetMapping("prop")
    public PatternProperties properties() {
        return properties;
    }

    @GetMapping("now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User findById(Long id, String truth) {
        System.out.println("truth: " + truth);
        return userService.queryById(id);
    }
}
