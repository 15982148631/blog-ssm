package com.wyf.blog.ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author wyf
 * @ClassName SsmApp
 * @Description //TODO
 * @Date 2020/11/13 14:31
 * @Version 1.0.0
 */
@SpringBootApplication
@EnableOpenApi//开启swagger
@EnableTransactionManagement//开启事务
@MapperScan(basePackages = "com.wyf.blog.ssm.mapper")//开启mybatis扫描
public class SsmApp {

    public static void main(String[] args) {
        SpringApplication.run(SsmApp.class,args);
    }
}
