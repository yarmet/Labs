package com.company.labs34;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@Slf4j
@EnableAutoConfiguration
@ImportResource("classpath:config.xml")
@ComponentScan(basePackages = {"com.company.labs34", "com.company.database"})
public class Lab34starter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Lab34starter.class, args);
        log.info("application 'labs34' started ");
    }

}
