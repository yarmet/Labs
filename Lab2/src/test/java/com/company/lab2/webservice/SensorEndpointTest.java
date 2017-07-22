package com.company.lab2.webservice;

import com.company.lab2.Lab2Starter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;


import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;

import javax.xml.transform.Source;

/**
 * Created by khamedov on 12.07.2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Lab2Starter.class)
public class SensorEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;
    private Resource xsdSchema = new ClassPathResource("schema1.xsd");

    @Before
    public void init() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }


    @Test
    public void sensorData() throws Exception {

        Source requestPayload = new StringSource("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ns2:itemRequest xmlns:ns2=\"http://localhost:8080/webservices\">\n" +
                "    <items>\n" +
                "        <item time=\"2017-07-20T17:41:36.833+03:00\" value=\"4.6\" deviceId=\"191\" parameterId=\"temperature\"/>\n" +
                "        <item time=\"2017-07-20T17:41:36.833+03:00\" value=\"1.4\" deviceId=\"195\" parameterId=\"humidity\"/>\n" +
                "    </items>\n" +
                "</ns2:itemRequest>\n");

        Source responsePayload = new StringSource("<ns3:itemResponse xmlns:ns3=\"http://localhost:8080/webservices\">" +
                "<code>200</code>" +
                "<items>" +
                "<item deviceId=\"191\" parameterId=\"temperature\" time=\"2017-07-20T17:41:36.833+03:00\" value=\"4.6\"/>" +
                "<item deviceId=\"195\" parameterId=\"humidity\" time=\"2017-07-20T17:41:36.833+03:00\" value=\"1.4\"/>" +
                "</items>" +
                "</ns3:itemResponse>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

}