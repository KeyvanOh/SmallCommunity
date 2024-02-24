package main4;

import java.io.*;
import java.net.*;


import com.fasterxml.jackson.core.*;

//import com.fasterxml.jackson.core.io.DataOutputAsStream;

public class Sender2 extends Thread {
    Socket socket;
    DataOutputStream out;
    //String name;
    
    //JsonFactory jsonFactory;
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    JsonGenerator jsonGeneratorForClose;
    
    public Sender2(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            //out = new OutputStream(socket.getOutputStream());
            
            
            Print.append("[");
            Print.append(socket.getInetAddress());
            Print.append(socket.getPort());
            Print.append("]");
            
            //name = Print.getString();
            //String name = Print.getString();
            Mut.name = Print.getString();
            
            //System.out.println(name);
            
            /*
            StringBuilder print = new StringBuilder();
            print.append("[");
            print.append(socket.getInetAddress());
            print.append(socket.getPort());
            print.append("]");
            */
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //String temp = new String();
        //String input = "SALAM.";
        while(out != null) {
            try {
                //Thread.sleep(100);
                Thread.sleep(1000);
                //String chat = Fn.readText(Const.FILE_NAME_CHAT);
                //String chat = "SALAM!";
                
                /*
                if (chat.length() == 0) {
                    continue;
                } else {
                    String chatScreen = Fn.readText(Const.FILE_NAME_RECEIVE);
                    chatScreen = chatScreen.concat(chat);
                    Fn.writeText(chatScreen, Const.FILE_NAME_RECEIVE);
                    
                    Print.append(chat);
                    
                    //String temp = name.concat(chat);
                    String temp = Mut.name.concat(chat);
                    System.out.println("temp: " + temp);
                    //out.writeUTF(name.concat(chat));
                    out.writeUTF(temp);
                    
                    Fn.writeText("", Const.FILE_NAME_CHAT);
                };
                */
                
                /*
                if (temp.equals(input) == false) {
                    temp = input;
                    //out.writeUTF(temp);
                    
                    
                    JsonFactory jsonFactory = new JsonFactory();
                    //JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(
                        //new File("serverIp.json"), JsonEncoding.UTF8
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("serverIp", Mut.serverIp);
                    jsonGenerator.writeStringField("port", Mut.port);
                    jsonGenerator.writeEndObject();
                    //jsonGenerator.close();
                };                    
                */
                
                
                boolean connection = false;
                //JsonFactory jsonFactory = new JsonFactory();
                JsonParser jsonParser = jsonFactory.createJsonParser(new File("connection.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    
                    /*
                    String fieldname = jsonParser.getCurrentName();
                    if ("userIp".equals(fieldname)) {
                        jsonParser.nextToken();
                        System.out.println(jsonParser.getText());
                    };
                    if ("serverIp".equals(fieldname)) {
                        jsonParser.nextToken();
                        System.out.println(jsonParser.getText());
                    };
                    if ("port".equals(fieldname)) {
                        jsonParser.nextToken();
                        System.out.println(jsonParser.getText());
                    };
                    if ("connection".equals(fieldname)) {
                        jsonParser.nextToken();
                        System.out.println(jsonParser.getBooleanValue());
                    };
                    */
                    
                    if ("connection".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        //System.out.println(jsonParser.getBooleanValue());
                        connection = jsonParser.getBooleanValue();
                    };
                };
                
                boolean request = false;
                //JsonParser jsonParser = jsonFactory.createJsonParser(new File("request.json"));
                jsonParser = jsonFactory.createJsonParser(new File("request.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("request".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        request = jsonParser.getBooleanValue();
                    };
                };
                
                if (request == true) {
                    System.out.println("Sender2-1 request: " + request);
                } else {
                    System.out.println("Sender2-1 request: " + request);
                };
                
                
                //System.out.println(Mut.request);
                //if (Mut.request == true) {
                //if (connection == true) {
                //System.out.println("connection: " + connection + ", request: " + request);
                if (connection == true && request == true) {
                //if (connection == true) {
                //if (Mut.request == true) {
                    //System.out.println(Mut.request);
                    //System.out.println("Sender2 request: " + Mut.request);
                    System.out.println("Sender2 request: " + request);
                    //JsonFactory jsonFactory = new JsonFactory();
                    //jsonFactory = new JsonFactory();
                    //JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    //JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        //new File("serverIp.json"), JsonEncoding.UTF8
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("serverIp", Mut.serverIp);
                    jsonGenerator.writeStringField("port", Mut.port);
                    jsonGenerator.writeEndObject();
                    //jsonGenerator.close();
                    jsonGenerator.flush();
                    
                    //Mut.request = false;
                    
                    /*
                    //JsonFactory jsonFactory = new JsonFactory();
                    //JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    jsonGenerator = jsonFactory.createGenerator(
                        new File("connection.json"), JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeBooleanField("connection", false);
                    jsonGenerator.writeEndObject();
                    jsonGenerator.close();
                    */
                    
                    
                    jsonGeneratorForClose = jsonFactory.createGenerator(
                        new File("request.json"), JsonEncoding.UTF8
                    );
                    jsonGeneratorForClose.writeStartObject();
                    jsonGeneratorForClose.writeBooleanField("request", false);
                    jsonGeneratorForClose.writeEndObject();
                    jsonGeneratorForClose.close();
                    //jsonGeneratorForClose.flush();
                    
                };
                
                
                
                
                
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender2 err\n");
                break;
            };
        };
        System.out.println("out is null.");
    }
}
