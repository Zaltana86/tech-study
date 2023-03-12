package com.ivx;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 11:38
 */
@Data
@Accessors(chain = true)
public class UserDTO {
    private Long id;
    private String username;
    private Integer age;
}
