package com.ivx.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/8/22 11:09
 */
@ApiModel(value = "用户登陆表单对象")
@Data(staticConstructor = "of")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty(value = "用户名", required = true, example = "root")
    private String userName;
    @ApiModelProperty(value = "密码", required = true, example = "123.com")
    private String password;
}
