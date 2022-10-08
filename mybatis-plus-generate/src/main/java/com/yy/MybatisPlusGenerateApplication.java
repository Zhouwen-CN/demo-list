package com.yy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yy.mapper")
public class MybatisPlusGenerateApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusGenerateApplication.class, args);
    }
}
