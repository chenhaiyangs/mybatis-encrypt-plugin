package com.chenhaiyang.plugin.mybatis.sensitive.test.all;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.chenhaiyang.plugin.mybatis.sensitive.test")
@SpringBootApplication
public class SpringBootTestApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootTestApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
