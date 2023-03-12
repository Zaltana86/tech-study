package com.ivx;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 11:37
 */
@Data
@Accessors(chain = true)
public class UserEntity {
    private Long id;
    private String username;
    private Integer age;
    private String password;
    private List<Subject1> subject1List;
}
