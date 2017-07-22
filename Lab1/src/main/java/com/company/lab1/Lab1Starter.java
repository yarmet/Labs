package com.company.lab1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@Slf4j
@EnableAutoConfiguration
@ImportResource({"classpath:config.xml"})
@ComponentScan(basePackages = {"com.company.lab1"})
@EntityScan(basePackages = {"com.company.database"})
public class Lab1Starter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Lab1Starter.class, args);
        log.info("application started ");
    }

}

