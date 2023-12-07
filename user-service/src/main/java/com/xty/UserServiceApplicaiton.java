package com.xty;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class UserServiceApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplicaiton.class, args);
        System.out.println("user-service启动完成...");
    }
}
