package com.example.mall_modified_version;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//整体扫描 不用一个个写@Mapper
@MapperScan(basePackages = "com.example.mall_modified_version.dao")
@Import(BCryptPasswordEncoder.class)
public class MallModifiedVersionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallModifiedVersionApplication.class, args);
    }

}
