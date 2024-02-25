package main1;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.*;

public class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    JsonGenerator jsonGeneratorForClose;
    
    public Sender(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            
            Print.append("[");
            Print.append(socket.getInetAddress());
            Print.append(socket.getPort());
            Print.append("]");
            
            Mut.name = Print.getString();
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while (out != null) {
            boolean connection = false;
            boolean request = false;
            JsonParser jsonParser;
            try {
                Thread.sleep(1000);
                
                jsonParser = jsonFactory.createJsonParser(new File("connection.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("connection".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        connection = jsonParser.getBooleanValue();
                    }; 
                };
                
                jsonParser = jsonFactory.createJsonParser(new File("request.json"));
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("request".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        request = jsonParser.getBooleanValue();
                    };
                };
                System.out.println("Sender request in run: " + request);
                
                if (connection && request) {
                    System.out.println("connection&request are true.");
                    
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", Mut.yourIp);
                    jsonGenerator.writeStringField("serverIp", Mut.serverIp);
                    jsonGenerator.writeStringField("port", Mut.port);
                    jsonGenerator.writeEndObject();
                    jsonGenerator.flush();
                    
                    //jsonGeneratorForClose = jsonFactory.createJsonGenerator(
                    jsonGeneratorForClose = jsonFactory.createGenerator(
                        new File("request.json"), JsonEncoding.UTF8
                    );
                    jsonGeneratorForClose.writeStartObject();
                    jsonGeneratorForClose.writeBooleanField("request", false);
                    jsonGeneratorForClose.writeEndObject();
                    jsonGeneratorForClose.close();
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