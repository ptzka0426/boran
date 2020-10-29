package com.boran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories //启用SpringData jpa，开启持久化
@SpringBootApplication
public class BoranSystemApplication /*extends SpringBootServletInitializer */{

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BoranSystemApplication.class);
    }*/
    public static void main(String[] args) {
        SpringApplication.run(BoranSystemApplication.class, args);
    }
}
