package com.ivx;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 11:38
 */
@Data
@Accessors(chain = true)
public class UserVO {
    private Long id;
    private String username;
    private String age;
    private List<Subject2> subject1List;

}
