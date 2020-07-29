package com.half.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.half.movie")
@EnableTransactionManagement//开启事务管理
@MapperScan(basePackages = "com.half.movie")
@EnableSwagger2
public class MovieWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MovieWebApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(MovieWebApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }

    /*@Bean
    public IdWorker idWorker (){
        return new IdWorker();
    }*/
}
