package com.company.labs34.lab4;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.company.database.models.Parameter;
import com.company.database.models.Sensor;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khamedov on 19.07.2017.
 */

@Slf4j
@Component
public class EntitiesCashe {


    @Value("${restUrl.sensors}")
    private String getSensorsUrl;

    @Value("${restUrl.parameters}")
    private String getParametersUrl;


    @Getter
    private Map<String, Parameter> parametersMap = new HashMap<>();

    @Getter
    private Map<Long, Sensor> sensorsMap = new HashMap<>();


    @PostConstruct
    private void init() {
        try {
            Client client = getJerseyClient();
            getParameters(client).forEach(parameter -> parametersMap.put(parameter.getParameterId(), parameter));
            getSensors(client).forEach(sensor -> sensorsMap.put(sensor.getId(), sensor));
        } catch (Exception e) {
            log.error(null, e);
        }
    }


    private Client getJerseyClient() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        return Client.create(config);
    }


    private List<Sensor> getSensors(Client client) {
        WebResource resource = client.resource(getSensorsUrl);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
        return response.getEntity(new GenericType<List<Sensor>>() {
        });
    }

    private List<Parameter> getParameters(Client client) {
        WebResource resource = client.resource(getParametersUrl);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
        return response.getEntity(new GenericType<List<Parameter>>() {
        });
    }

}
