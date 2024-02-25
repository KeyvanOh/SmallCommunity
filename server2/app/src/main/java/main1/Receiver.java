package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import org.apache.commons.io.FileUtils;

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
        //while (in != null) {
        //while (in != null && in.available() != 0) {
        while (in != null) {
            System.out.println("Thread Receiver of server2 is ALIVE.");
            //Thread.sleep(1000);
            boolean delete = false;
            String postNumberDelete = "";
            try {
                Thread.sleep(1000);
                //System.out.println("Thread Receiver of server2 is ALIVE.");
                System.out.println("Receiver yet 1");
                //JsonFactory jsonFactory = new JsonFactory();
                //JsonParser jsonParser = jsonFactory.createJsonParser(in);
                //System.out.println(in);
                //System.out.println(in.available());
                //if (in.available() == 0) {
                //    break;
                //};
                jsonParser = jsonFactory.createJsonParser(in);
                System.out.println("Receiver yet 1-01");
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    System.out.println("Receiver yet 1-1");
                    String fieldName = jsonParser.getCurrentName();
                    if (fieldName != null) {
                        System.out.println("Receiver yet 1-2");
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
                            case "delete": {
                                jsonParser.nextToken();
                                //System.out.println(jsonParser.getBooleanValue());
                                delete = jsonParser.getBooleanValue();
                                //Mut.posted = jsonParser.getText();
                                break;
                            }
                            case "postNumberDelete": {
                                jsonParser.nextToken();
                                System.out.println("postNumberDelete: " + jsonParser.getText());
                                postNumberDelete = jsonParser.getText();
                                //Mut.posted = jsonParser.getText();
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
                    } else {
                        System.out.println("Receiver yet 1-3");
                        //break;
                    };
                    System.out.println("Receiver yet 1-4");
                };
                System.out.println("Receiver yet 2");
                
                if (delete == false) {
                    
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
                    
                    
                } else {
                    System.out.println("delete is true!!!");
                    
                    
                    //File fileToDie = new File("filename.txt"); 
                    File fileToDie = new File("posts/" + postNumberDelete + ".json"); 
                    
                    FileUtils.forceDelete(fileToDie);
                    
                    /*
                    while (true) {
                        if (fileToDie.delete()) { 
                            System.out.println(fileToDie.getName() + " dead now.");
                            break;
                        } else {
                            System.out.println("Failed to delete " + fileToDie.getName());
                        };                 
                        
                    };
                    */
                    
                    
                };
                
                
                System.out.println("Receiver yet 3");
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}