package com.company.lab2.webservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.company.lab2.logsTools.Loggable;
import com.company.webservice.EndpointUrl;
import com.company.webservice.entities.ItemRequest;
import com.company.webservice.entities.ItemResponse;


@Endpoint
public class SensorEndpoint {


    @Loggable
    @ResponsePayload
    @PayloadRoot(namespace = EndpointUrl.NAMESPACE_URI, localPart = "itemRequest")
    public ItemResponse sensorData(@RequestPayload ItemRequest sensorRequest) {
        ItemResponse sensorResponse = new ItemResponse();
        sensorResponse.setCode(200);
        sensorResponse.setItems(sensorRequest.getItems());
        return sensorResponse;
    }

}
