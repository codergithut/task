package com.factory.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tianjian on 2020/1/11.
 */
@SpringBootApplication
@EnableSwagger2
public class Example {

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }

}
