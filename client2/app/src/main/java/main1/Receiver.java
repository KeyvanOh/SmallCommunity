package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    JsonParser jsonParser;
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(in != null) {
            System.out.println("Thread Receiver of client2 is ALIVE.");
            try {
                Thread.sleep(1000);
                
                
                //Map<String,Object> dataMap = new Map<String,Object>();
                
                //ObjectMapper mapper = new ObjectMapper();
                
                //MyClass[] myObjects = mapper.readValue(json, MyClass[].class);
                //Map<String,Object>[] myObjects = mapper.readValue(json, Map<String,Object>[].class);
                //Map<String,Object>[] myObjects = mapper.readValue(in, Map<String,Object>[].class);
                
                //List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
                //List<MyClass> myObjects = mapper.readValue(in, new TypeReference<List<MyClass>>(){});
                //List<Map<String,Object>> myObjects = mapper.readValue(in, new TypeReference<List<Map<String,Object>>>(){});
                
                
                
                
                
                jsonGenerator = jsonFactory.createGenerator(
                    new File("list.json"), JsonEncoding.UTF8
                );
                jsonGenerator.writeStartArray();
                //jsonGenerator.writeStartObject();
                /*
                jsonGenerator.writeStartObject();
                jsonGenerator.writeBooleanField("connection", false);
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
                */
                
                //System.out.println("Receiver step 1");
                //JsonFactory jsonFactory = new JsonFactory();
                //JsonParser jsonParser = jsonFactory.createJsonParser(in);
                jsonParser = jsonFactory.createJsonParser(in);
                //while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    if (jsonParser.currentToken() == JsonToken.START_OBJECT) {
                        jsonGenerator.writeStartObject();
                    } else if (jsonParser.currentToken() == JsonToken.END_OBJECT) {
                        jsonGenerator.writeEndObject();
                    };
                    //System.out.println("Receiver step 1-2");
                    String fieldName = jsonParser.getCurrentName();
                    if (fieldName != null) {
                        //System.out.println("Receiver step 1-3");
                        //System.out.println(fieldName);
                        switch (fieldName) {
                            /*
                            case "userIp": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                                break;
                            }
                            case "serverIp": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                                break;
                            }
                            case "port": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                                break;
                            }
                            */
                            case "postNumber": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                jsonGenerator.writeStringField("postNumber", jsonParser.getText());
                                break;
                            }
                            case "userIp": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                jsonGenerator.writeStringField("userIp", jsonParser.getText());
                                break;
                            }
                            case "date": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                jsonGenerator.writeStringField("date", jsonParser.getText());
                                break;
                            }
                            case "post": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                jsonGenerator.writeStringField("post", jsonParser.getText());
                                break;
                            }
                            default: {
                            }
                        };
                        
                        //System.out.println("Receiver step 1-4");
                        
                        //System.out.println(fieldName);
                    } else {
                        //System.out.println("fieldName in the Receiver is null");
                        //break;
                        //jsonParser.nextToken();
                    };
                };
                jsonGenerator.writeEndArray();
                //jsonGenerator.flush();
                jsonGenerator.close();
                
                
                System.out.println("Receiver step 2");
                
                
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}