package com.company.lab2.webservice;


import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import com.company.webservice.EndpointUrl;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {


    @Bean
    public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "sensor")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sensorSchema) {
//        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
//        wsdl11Definition.setWsdl(new ClassPathResource("/schema/MyWsdl.wsdl")); //wsdl location
//        return wsdl11Definition;

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SchemaPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(EndpointUrl.NAMESPACE_URI);
        wsdl11Definition.setSchema(sensorSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema sensorSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema1.xsd"));
    }

}
