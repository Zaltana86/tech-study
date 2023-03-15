package cn.itcast.user.web.feign;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.user.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/14 19:45
 */

@RestController
public class UserFeignController implements UserClient {
    @Resource
    private UserService userService;
    @Override
    public User findById(Long id) {
        return userService.queryById(id);
    }
}
