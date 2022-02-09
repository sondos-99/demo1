package com.example.demo.ConverterJSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public  class ConverterJSON {
    static ObjectMapper objectMapper;
     static String returnValue;

    public ConverterJSON() {
         objectMapper = new ObjectMapper();
        this.returnValue = null;
    }

    public static String convertToJSON(Object object) throws JsonProcessingException {
        returnValue = objectMapper.writeValueAsString(object);
        return returnValue;
    }

    public  static Object convertFromJSON(Class object, String json) throws JsonProcessingException {

        Object returnedObject = objectMapper.readValue(json, object);
        return returnedObject;
    }
}
