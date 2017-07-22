package com.company.lab5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by khamedov on 20.07.2017.
 */


@Slf4j
@Configuration
@ComponentScan(basePackages = "com.company.lab5")
@EnableAutoConfiguration
@ImportResource("classpath:config.xml")


public class Lab5Starter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Lab5Starter.class, args);
        log.info("application 'lab5' started ");
    }

}
