package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser;
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
        while (in != null) {
            System.out.println("Thread Receiver of server2 is ALIVE.");
            //Thread.sleep(1000);
            try {
                Thread.sleep(1000);
                //JsonFactory jsonFactory = new JsonFactory();
                //JsonParser jsonParser = jsonFactory.createJsonParser(in);
                jsonParser = jsonFactory.createJsonParser(in);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    if (fieldName != null) {
                        switch (fieldName) {
                            case "userIp": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                Mut.userIp = jsonParser.getText();
                                break;
                            }
                            case "date": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                Mut.date = jsonParser.getText();
                                break;
                            }
                            case "idTime": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                Mut.idTime = jsonParser.getText();
                                break;
                            }
                            case "post": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getText());
                                Mut.posted = jsonParser.getText();
                                break;
                            }
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
                            default: {
                            }
                        };
                    };
                };
                System.out.println(Mut.userIp);
                System.out.println(Mut.date);
                System.out.println(Mut.idTime);
                System.out.println(Mut.posted);
                
                Mut.postNumber++;
                
                File theDir = new File("posts");
                //if (!theDir.exists()){
                if (theDir.exists() == false){
                    theDir.mkdirs();
                };
                //String filePathName = "posts/" + Mut.postNumber + ".json";
                //String filePathName = "posts/" + String.format("%02d", Mut.postNumber) + ".json";
                String filePathName = "posts/" + String.format("%04d", Mut.postNumber) + ".json";
                jsonGenerator = jsonFactory.createGenerator(
                    //new File("post.json"), JsonEncoding.UTF8
                    //new File("posts/post.json"), JsonEncoding.UTF8
                    new File(filePathName), JsonEncoding.UTF8
                );
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("userIp", Mut.userIp);
                jsonGenerator.writeStringField("date", Mut.date);
                jsonGenerator.writeStringField("idTime", Mut.idTime);
                jsonGenerator.writeStringField("post", Mut.posted);
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
                
                
                jsonGenerator = jsonFactory.createGenerator(
                    //new File("request.json"), JsonEncoding.UTF8
                    //new File(filePathName), JsonEncoding.UTF8
                    new File("postNumber.json"), JsonEncoding.UTF8
                );
                jsonGenerator.writeStartObject();
                //jsonGenerator.writeBooleanField("request", false);
                //jsonGenerator.writeIntField("postNumber", false);
                //jsonGenerator.writeIntField("postNumber", Mut.postNumber);
                jsonGenerator.writeNumberField("postNumber", Mut.postNumber);
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
                
                
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}