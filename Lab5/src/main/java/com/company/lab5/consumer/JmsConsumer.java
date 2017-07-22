package com.company.lab5.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.xml.bind.JAXBException;

import org.apache.activemq.*;

/**
 * Created by khamedov on 20.07.2017.
 */
@Slf4j
@Component
public class JmsConsumer {

    @Value("${activemq.url}")
    private String activeMQUrl;

    @Value("${activemq.queue}")
    private String queueName;


    @PostConstruct
    private void init() {
        receiveMessageFromQueue();
    }


    void receiveMessageFromQueue() {

        CamelContext camelContext = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUrl);
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws JAXBException {
                    fromF("jms:queue:%s", queueName).choice()
                            .when(p -> JsonValidator.isValidJSON(p.getIn().getBody(String.class)))
                            .process(p -> log.info("Received valid message." + p.getIn().getBody()))
                            .otherwise()
                            .process(p -> log.info("Received not valid message." + p.getIn().getBody()));
                }
            });
            camelContext.start();
        } catch (Exception e) {
            log.error(null, e);
        }
    }

}
