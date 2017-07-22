package com.company.lab1.controllers;

/**
 * Created by khamedov on 10.07.2017.
 */

import com.company.lab1.services.ParameterService;
import com.company.lab1.services.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.database.models.Parameter;
import com.company.database.models.Sensor;


@Slf4j
@RestController
@RequestMapping(name = "/")
public class Controller {

    @Autowired
    private ParameterService parameterService;


    @Autowired
    private SensorService sensorService;


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "its works";
    }


    @RequestMapping(value = "getSensors", method = RequestMethod.GET)
    public ResponseEntity<?> getSensors() {
        return ResponseEntity.ok(sensorService.findAll());
    }

    @RequestMapping(value = "getParameters", method = RequestMethod.GET)
    public ResponseEntity<?> getParameters() {
        return ResponseEntity.ok(parameterService.findAll());
    }
}