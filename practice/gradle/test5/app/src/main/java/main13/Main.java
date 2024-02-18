package main13;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main13.");
        
        Main tester = new Main();
        try {
            JsonFactory jsonFactory = new JsonFactory();
            
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                new File("student.json"), JsonEncoding.UTF8
            );
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
            
            JsonParser jsonParser = jsonFactory.createParser(new File("student.json"));
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("name".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getText());
                };
                if ("age".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getNumberValue());
                };
                if ("verified".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getBooleanValue());
                };
                if ("marks".equals(fieldName)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println(jsonParser.getNumberValue());
                    };
                };
            };
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
}