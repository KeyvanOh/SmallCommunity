package main11;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main11.");
        
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("student.json"), JsonEncoding.UTF8);
            
            jsonGenerator.writeStartObject();
            
            jsonGenerator.writeStringField("name", "Mahesh Kumar");
            
            jsonGenerator.writeNumberField("age", 21);
            
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
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
}