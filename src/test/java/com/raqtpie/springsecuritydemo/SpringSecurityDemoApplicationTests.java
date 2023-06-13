package com.raqtpie.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void loadPassword() {
        System.out.println(new BCryptPasswordEncoder().encode("password1"));
        System.out.println(new BCryptPasswordEncoder().encode("password2"));
        System.out.println(new BCryptPasswordEncoder().encode("password3"));
    }

}
