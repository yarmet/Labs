package com.company.lab2.generator;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


/**
 * Created by khamedov on 13.07.2017.
 */

@Slf4j
public class XsdGenerator {

    static public void generate(Class<?>... classes) {


        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(classes);
            SchemaOutputResolver sor = new SchemaOutputResolver() {
                public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
                    File file = new File(suggestedFileName);
                    StreamResult result = new StreamResult(file);
                    result.setSystemId(file.toURI().toURL().toString());
                    return result;
                }
            };
            jaxbContext.generateSchema(sor);
        } catch (IOException | JAXBException e) {
            log.error("can't generate XSD schema from java classes ", e);
        }
    }

}










//    Item item = new Item();
//        item.setDeviceId(191);
//                item.setParameterId("temperature");
//                item.setTime(new Date());
//                item.setValue(4.6);
//
//                Item item2 = new Item();
//                item2.setDeviceId(195);
//                item2.setParameterId("humidity");
//                item2.setTime(new Date());
//                item2.setValue(1.4);
//
//
//                ItemRequest itemRequest = new ItemRequest();
//                itemRequest.getItems().add(item);
//                itemRequest.getItems().add(item2);
// try {
//         File file = new File("request.xml");
//         JAXBContext jaxbContext = JAXBContext.newInstance(ItemRequest.class);
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//        // output pretty printed
//        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        jaxbMarshaller.marshal(itemRequest, file);
//        } catch (Exception e) {
//        log.error(null, e);
//        }