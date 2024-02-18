package main10;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main10.");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = "{\"name\":\"JK O\", \"age\":30, \"verified\":false, \"marks\":[100, 90, 85]}";
            JsonNode rootNode = mapper.readTree(jsonString);
            
            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: " + nameNode.textValue());
            
            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.intValue());
            
            JsonNode verifiedNode = rootNode.path("verified");
            System.out.println("Verified: " + (verifiedNode.booleanValue() ? "Yes" : "No"));
            
            JsonNode marksNode = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode.elements();
            System.out.print("Marks: [ ");
            while (iterator.hasNext()) {
                JsonNode marks = iterator.next();
                System.out.print(marks.intValue() + " ");
            };
            System.out.println("]");
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
}