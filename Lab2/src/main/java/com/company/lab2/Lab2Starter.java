package com.company.lab2;

import com.company.lab2.generator.XsdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import com.company.webservice.entities.ItemRequest;
import com.company.webservice.entities.ItemResponse;

/**
 * Created by khamedov on 20.07.2017.
 */


@Slf4j
@EnableAutoConfiguration
@ImportResource("classpath:config.xml")
@ComponentScan(basePackages = "com.company.lab2")
public class Lab2Starter {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Lab2Starter.class, args);
        XsdGenerator.generate(ItemRequest.class, ItemResponse.class);
        log.info("application 'lab2' started ");
    }

}
