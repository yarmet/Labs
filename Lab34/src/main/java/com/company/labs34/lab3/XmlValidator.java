package com.company.labs34.lab3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ruslan on 16.07.2017.
 */

@Slf4j
@Component
public class XmlValidator {

    private static String SCHEMA_NAME = "schema1.xsd";
    private Validator validator;

    @PostConstruct
    private void init() {
        ClassLoader classLoader = XmlValidator.this.getClass().getClassLoader();
        File xsdFile = new File(classLoader.getResource(SCHEMA_NAME).getFile());
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(xsdFile);
            validator = schema.newValidator();
        } catch (SAXException e) {
            log.error(null, e);
        }
    }

    public boolean validate(String xmlString) {
        try {
            validator.validate(new StreamSource(new StringReader(xmlString)));
        } catch (IOException | SAXException e) {
            log.error(null, e);
            return false;
        }
        return true;
    }
}

