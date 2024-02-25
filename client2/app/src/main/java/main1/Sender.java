package main1;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.*;

public class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    JsonParser jsonParser;
    //JsonGenerator jsonGenerator2;
    //JsonGenerator jsonGeneratorForClose;
    
    public Sender(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            /*
            Print.append("[");
            Print.append(socket.getInetAddress());
            Print.append(socket.getPort());
            Print.append("]");
            
            Mut.name = Print.getString();
            */
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //JsonParser jsonParser;
        while (out != null) {
            System.out.println("Thread Sender of client2 is ALIVE.");
            boolean connection = false;
            boolean request = false;
            //JsonParser jsonParser;
            //JsonParser jsonParser2;
            //boolean pass = true;
            try {
                Thread.sleep(1000);
                //System.out.println("alive yet 1");
                jsonParser = jsonFactory.createJsonParser(new File("connection.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("connection".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        connection = jsonParser.getBooleanValue();
                    }; 
                };
                //System.out.println("alive yet 2");
                jsonParser = jsonFactory.createJsonParser(new File("request.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("request".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        request = jsonParser.getBooleanValue();
                    };
                };
                System.out.println("Sender request in run: " + request);
                //System.out.println("alive yet 3");
                if (connection && request) {
                    System.out.println("connection&request are true.");
                    
                    
                    /*
                    //System.out.println("alive yet 4");
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("serverIp", Mut.serverIp);
                    jsonGenerator.writeStringField("port", Mut.port);
                    jsonGenerator.writeEndObject();
                    jsonGenerator.flush();
                    */
                    
                    
                    
                    
                    
                    
                    
                    /*
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("date", String.valueOf(new java.util.Date()));
                    jsonGenerator.writeStringField("idTime", Const.idTime);
                    jsonGenerator.writeStringField("post", Mut.posting);
                    jsonGenerator.writeEndObject();
                    jsonGenerator.close();
                    */
                    
                    //System.out.println("alive yet 5");
                    
                    //jsonFactory = new JsonFactory();
                    //JsonFactory jsonFactory2 = new JsonFactory();
                    //jsonParser2 = jsonFactory.createJsonParser(new File("post.json"));
                    //jsonParser2 = jsonFactory2.createJsonParser(new File("post.json"));
                    //jsonParser = jsonFactory.createJsonParser(new File("post.json"));
                    //JsonFactory jsonFactory2 = new JsonFactory();
                    //JsonParser jsonParser2 = jsonFactory2.createJsonParser(new File("post.json"));
                    //JsonFactory jsonFactory2 = new JsonFactory();
                    jsonParser = jsonFactory.createJsonParser(new File("post.json"));
                    //System.out.println("alive yet 5-1");
                    //System.out.println(jsonParser);
                    //System.out.println(jsonParser2);
                    
                    boolean pass = true;
                    
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        //System.out.println("alive yet 5-2");
                        String fieldName = jsonParser.getCurrentName();
                        //System.out.println(fieldName);
                        //System.out.println("alive yet 5-3");
                        if (fieldName != null) {
                            //System.out.println("alive yet 5-4");
                            switch (fieldName) {
                                case "userIp": {
                                    jsonParser.nextToken();
                                    //System.out.println(jsonParser.getText());
                                    Mut.yourIp = jsonParser.getText();
                                    break;
                                }
                                case "date": {
                                    jsonParser.nextToken();
                                    //.out.println(jsonParser.getText());
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
                                    Mut.posting = jsonParser.getText();
                                    break;
                                }
                                default: {
                                }
                            };
                        } else {
                            //System.out.println("fieldName in the Sender is null");
                            //pass = false;
                            //break;
                            //jsonParser2.nextToken();
                        };
                    };
                    /*
                    System.out.println(Mut.yourIp);
                    System.out.println(Mut.date);
                    System.out.println(Mut.idTime);
                    System.out.println(Mut.posting);
                    */
                    
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("date", Mut.date);
                    jsonGenerator.writeStringField("idTime", Mut.idTime);
                    jsonGenerator.writeStringField("post", Mut.posting);
                    jsonGenerator.writeEndObject();
                    jsonGenerator.flush();
                    
                    
                    
                    
                    
                    //System.out.println("alive yet 6");
                    
                    
                    
                    
                    /*
                    //jsonGeneratorForClose = jsonFactory.createJsonGenerator(
                    jsonGeneratorForClose = jsonFactory.createGenerator(
                        new File("request.json"), JsonEncoding.UTF8
                    );
                    jsonGeneratorForClose.writeStartObject();
                    jsonGeneratorForClose.writeBooleanField("request", false);
                    jsonGeneratorForClose.writeEndObject();
                    jsonGeneratorForClose.close();
                    */
                    
                    if (pass == true) {
                        //jsonFactory = new JsonFactory();
                        jsonGenerator = jsonFactory.createGenerator(
                            new File("request.json"), JsonEncoding.UTF8
                        );
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeBooleanField("request", false);
                        jsonGenerator.writeEndObject();
                        jsonGenerator.close();
                        //jsonGenerator.flush();
                        
                    };
                    
                    //System.out.println("alive yet survived");
                };
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
        System.out.println("out is null");
    }
}