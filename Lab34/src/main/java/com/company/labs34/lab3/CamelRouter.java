package com.company.labs34.lab3;

import com.company.labs34.lab4.Converter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.jms.*;

import org.apache.activemq.*;
import com.company.webservice.entities.ItemResponse;

/**
 * Created by khamedov on 14.07.2017.
 */
@Slf4j
@Component
public class CamelRouter {

    @Value("${ftp.url}")
    private String ftpUrl;

    @Value("${ftp.incorrect_files_url}")
    private String incorrectFilesUrl;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${queue.path}")
    private String queuePath;

    @Value("${queue.name}")
    private String queueName;

    @Value("${webservice.path}")
    private String webServicePath;

    @Autowired
    private XmlValidator requestValidator;

    @Autowired
    private Converter converter;

    @PostConstruct
    private void init() {

        CamelContext camelContext = new DefaultCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(queuePath);
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        try {
            JAXBContext jaxbContextResponse = JAXBContext.newInstance(ItemResponse.class);
            DataFormat jaxbDataFormatSensorResponse = new JaxbDataFormat(jaxbContextResponse);

            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws JAXBException {

                    fromF("ftp://%s?username=%s&password=%s&delete=true", ftpUrl, username, password)
                            .choice()
                            .when(p -> requestValidator.validate(p.getIn().getBody(String.class)))
                            .toF("spring-ws:%s", webServicePath).unmarshal(jaxbDataFormatSensorResponse).bean(converter)
                            .split(body())
                            .marshal().json(JsonLibrary.Jackson)
                            .process(p -> {
                                p.getOut().setHeader("JMSType", "sensor values");
                                p.getOut().setBody(p.getIn().getBody(String.class));
                            })
                            .toF("jms:queue:%s", queueName)
                            .endChoice()
                            .otherwise().toF("ftp://%s?username=%s&password=%s", incorrectFilesUrl, username, password);

                }
            });
            camelContext.start();
        } catch (Exception e) {
            log.error(null, e);
        }
    }


}
