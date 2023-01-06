package com.ivx.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href='mailto:kl142857h@163.com'>kl142857h@163.com&lt;skyler&gt;</skyler></a>
 * @apiNote
 * @since 2022/9/22 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String password;
}
