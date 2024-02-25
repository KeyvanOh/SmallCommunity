package main1;

import java.io.*;

import javafx.application.Application;

import com.fasterxml.jackson.core.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main1.");
        
        /*
        //File theDir = new File("/path/directory");
        //File theDir = new File("/path/directory/posts");
        File theDir = new File("posts");
        //File theDir = new File("posts/post.json");
        if (!theDir.exists()){
            theDir.mkdirs();
        };
        */
        
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser;
        try {
            jsonParser = jsonFactory.createJsonParser(new File("postNumber.json"));
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if ("postNumber".equals(jsonParser.getCurrentName())) {
                    jsonParser.nextToken();
                    Mut.postNumber = jsonParser.getIntValue();
                };
            };
        } catch(Exception e) {
            e.printStackTrace();
        };
        System.out.println("Recent postNumber: " + Mut.postNumber);
        
        
        
        
        
        Server server = new Server();
        server.start();
        
        GUI.launch(GUI.class, args);
        
        System.exit(0);
    }
}