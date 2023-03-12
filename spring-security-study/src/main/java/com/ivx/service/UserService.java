package com.ivx.service;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author skyler
 * @apiNote
 * @since 2023/3/3 0:00
 */
@Service
public class UserService {

    @PreAuthorize("hasAuthority('p1')")
    public void testAuthMethod() {
        System.out.println("hello");
    }
}
