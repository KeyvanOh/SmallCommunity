package main12;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main12.");
        
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("student.json"), JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            
            jsonGenerator.writeStringField("name", "JK");
            jsonGenerator.writeNumberField("age", 30);
            jsonGenerator.writeBooleanField("verified", false);
            
            jsonGenerator.writeFieldName("marks");
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(100);
            jsonGenerator.writeNumber(90);
            jsonGenerator.writeNumber(85);
            jsonGenerator.writeEndArray();
            
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataMap = mapper.readValue(new File("student.json"), Map.class);
            
            System.out.println(dataMap.get("name"));
            System.out.println(dataMap.get("age"));
            System.out.println(dataMap.get("verified"));
            System.out.println(dataMap.get("marks"));
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
}