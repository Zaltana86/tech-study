package cn.itcast.feign.clients;


import cn.itcast.feign.config.DefaultFeignConfiguration;
import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userservice", path = "usercontext")
public interface UserClient {
    @GetMapping(DefaultFeignConfiguration.FEIGN_PREFIX + "/user/{id}")
    User findById(@PathVariable("id") Long id);
}
