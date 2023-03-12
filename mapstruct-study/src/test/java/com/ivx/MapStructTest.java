package com.ivx;


import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 11:42
 */

public class MapStructTest {
    @Test
    public void test() {
        UserEntity userEntity = new UserEntity().setId(1L).setUsername("skyler").setAge(18).setPassword("123.com").setSubject1List(Collections.singletonList(new Subject1(1L)));
        UserVO userVO = UserConvert.INSTANCE.entity2VO(userEntity);

        System.out.println(userVO);


        List<UserVO> userVOS = UserConvert.INSTANCE.entity2VOList(Collections.singletonList(userEntity));
        System.out.println(userVOS);

        // UserDTO userDTO = new UserDTO().setAge(18).setUsername("skyler");
        // UserEntity userEntity1 = UserConvert.INSTANCE.dto2Entity(userDTO);
        // System.out.println(userEntity1);
    }
}
