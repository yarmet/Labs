package com.company.lab1.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.company.database.models.Parameter;
import com.company.database.models.Sensor;
import com.company.lab1.Lab1Starter;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ruslan on 10.07.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Lab1Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Value("${local.server.port}")
    int port;


    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void getSensors() {
        List<Sensor> responses = restTemplate.getForObject("http://localhost:" + port + "/getSensors", List.class);
        assertNotNull(responses);
    }

    @Test
    public void getParameters() {
        List<Parameter> responses = restTemplate.getForObject("http://localhost:" + port + "/getParameters", List.class);
        assertNotNull(responses);
    }

}