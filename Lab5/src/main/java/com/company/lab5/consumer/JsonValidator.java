package com.company.lab5.consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

/**
 * Created by khamedov on 20.07.2017.
 */
@Slf4j
public class JsonValidator {


    public static boolean isValidJSON(final String json) {
        try {
            final JsonParser parser = new ObjectMapper().getJsonFactory()
                    .createJsonParser(json);
            while (parser.nextToken() != null) {
            }
            return true;
        } catch (IOException ioe) {
            log.error("received not valid json ", ioe);
        }
        return false;
    }


}
