package com.hsy.creatorservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hsy"})
@MapperScan("com.hsy.creatorservice.mapper")
public class CreatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreatorApplication.class, args);
    }
}
