package com.company.labs34.lab4;


import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.company.database.models.Coordinate;
import com.company.database.models.Parameter;
import com.company.database.models.Sensor;
import com.company.webservice.entities.ItemResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by khamedov on 19.07.2017.
 */

@Component
public class Converter {

    @Autowired
    private EntitiesCashe entitiesCasher;


    @Handler
    public List<ToJson> process(ItemResponse sensorResponse) throws Exception {

        return sensorResponse.getItems().stream().map(item -> {
            Sensor sensor = entitiesCasher.getSensorsMap().get(item.getDeviceId());
            Parameter parameter = entitiesCasher.getParametersMap().get(item.getParameterId());

            Coordinate coordinate = sensor.getCoordinate();

            return ToJson.builder()
                    .deviceName(sensor.getName())
                    .paramName(parameter.getName())
                    .value(String.valueOf(item.getValue()))
                    .deviceCoordinate(coordinate.getLatitude() + " " + coordinate.getLongitude())
                    .build();

        }).collect(Collectors.toList());
    }

}
