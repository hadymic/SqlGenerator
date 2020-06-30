package com.hadymic.sqlgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.gitee.sunchenbin.mybatis.actable.manager.*"})
//@MapperScan("com.gitee.sunchenbin.mybatis.actable.dao.*")
@ComponentScan("com.hadymic.sqlgenerator.*")
@MapperScan({"com.baomidou.mybatisplus.samples.quickstart.mapper","com.hadymic.sqlgenerator.mapper"})
@SpringBootApplication
public class SqlgeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlgeneratorApplication.class, args);
    }

}
