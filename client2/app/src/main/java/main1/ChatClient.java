package main1;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.core.*;

public class ChatClient {
    public static void client() {
        Socket socket = null;
        try {
            //Fn.writeText("", Const.FILE_NAME_CHAT);
            //Fn.writeText("", Const.FILE_NAME_RECEIVE);
            
            socket = new Socket(Mut.serverIp, Integer.parseInt(Mut.port));
            Print.print("Connected.\n");
            
            JsonFactory jsonFactory = new JsonFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                new File("connection.json"), JsonEncoding.UTF8
            );
            jsonGenerator.writeStartObject();
            jsonGenerator.writeBooleanField("connection", true);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            
            jsonGenerator = jsonFactory.createGenerator(
                new File("request.json"), JsonEncoding.UTF8
            );
            jsonGenerator.writeStartObject();
            jsonGenerator.writeBooleanField("request", true);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);
            
            sender.start();
            receiver.start();
        } catch(Exception e) {
            e.printStackTrace();
            Print.print("Connection failed.\n");
        };
    }
}