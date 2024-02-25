package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    JsonParser jsonParser;
    
    public Sender(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while (out != null) {
            System.out.println("Thread Sender of server2 is ALIVE.");
            
            
            
            
            
            
            
            
            
            
            
            
            
            //Thread.sleep(1000);
            try {
                Thread.sleep(1000);
                
                
                
                List<String> results = new ArrayList<String>();
                File theDir = new File("posts");
                if (theDir.exists() == false){
                    theDir.mkdirs();
                };
                //File[] files = new File("/path/to/the/directory").listFiles();
                //File[] files = new File("/path/to/the/directory").listFiles();
                File[] files = theDir.listFiles();
                //If this pathname does not denote a directory, then listFiles() returns null. 
                
                jsonGenerator = jsonFactory.createJsonGenerator(
                //jsonGenerator = jsonFactory.createGenerator(
                    out, JsonEncoding.UTF8
                    //new File("test.json"), JsonEncoding.UTF8
                );
                //jsonGenerator.writeStartObject();
                jsonGenerator.writeStartArray();
                for (File file : files) {
                    if (file.isFile()) {
                        results.add(file.getName());
                        
                        
                        //String str = new String();
                        String str = file.getName();
                        //str = str.substring(0, str.lastIndexOf('.'));
                        Mut.postNumberTemp = str.substring(0, str.lastIndexOf('.'));
                        //str += ".";
                        //Print.append(str);
                        Print.append(Mut.postNumberTemp);
                        //jsonParser = jsonFactory.createJsonParser(new File("post.json"));
                        jsonParser = jsonFactory.createJsonParser(file);
                        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                            String fieldName = jsonParser.getCurrentName();
                            if (fieldName != null) {
                                switch (fieldName) {
                                    case "userIp": {
                                        jsonParser.nextToken();
                                        Mut.userIp = jsonParser.getText();
                                        //str += jsonParser.getText();
                                        //str += " ";
                                        break;
                                    }
                                    case "date": {
                                        jsonParser.nextToken();
                                        Mut.date = jsonParser.getText();
                                        //str += jsonParser.getText();
                                        //str += " ";
                                        break;
                                    }
                                    case "idTime": {
                                        jsonParser.nextToken();
                                        Mut.idTime = jsonParser.getText();
                                        //str += jsonParser.getText();
                                        //str += " ";
                                        break;
                                    }
                                    case "post": {
                                        jsonParser.nextToken();
                                        Mut.posted = jsonParser.getText();
                                        //str += jsonParser.getText();
                                        //str += " ";
                                        break;
                                    }
                                    default: {
                                    }
                                };
                            };
                        };
                        Print.append(".");
                        /*
                        int lastIndex = Mut.posted.lastIndexOf('\n');
                        if (lastIndex < 0) {
                            Print.append(Mut.posted);
                        } else {
                            //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf('\\n')));
                            //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf('\n')));
                            Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf("\\n")));
                        };
                        */
                        //Print.append(Mut.posted.replace("\n", "").replace("\r", ""));
                        //Print.append(Mut.posted.replaceAll("\\r|\\n", ""));
                        Print.append(Mut.posted.replaceAll("\\r|\\n", " "));
                        
                        
                        //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf('\n')));
                        //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf("\n")));
                        //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf('\\n')));
                        //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf("\\n")));
                        //Print.append(Mut.posted.substring(0, Mut.posted.lastIndexOf('\\')));
                        //Print.append("\n");
                        str = Print.getAndReset();
                        
                        
                        System.out.println(str);
                        
                        
                        
                        
                        
                        //jsonGenerator = jsonFactory.createJsonGenerator(
                        //    out, JsonEncoding.UTF8
                        //);
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("postNumber", Mut.postNumberTemp);
                        jsonGenerator.writeStringField("userIp", Mut.userIp);
                        jsonGenerator.writeStringField("date", Mut.date);
                        jsonGenerator.writeStringField("post", Mut.posted);
                        jsonGenerator.writeEndObject();
                        
                        
                        
                        //jsonGenerator.flush();
                        
                        
                        
                        
                        
                    };
                };        
                System.out.println(results);
                //jsonGenerator.writeEndObject();
                jsonGenerator.writeEndArray();
                jsonGenerator.flush();
                //jsonGenerator.close();
                
                
                
                
                //Mut.request = true;
                if (Mut.request == true) {
                    /*
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", "123");
                    jsonGenerator.writeStringField("serverIp", "456");
                    jsonGenerator.writeStringField("port", "789");
                    jsonGenerator.writeEndObject();
                    jsonGenerator.flush();
                    */
                    
                    
                    
                    
                    Mut.request = false;
                };
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
    }
}