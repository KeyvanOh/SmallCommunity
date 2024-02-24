package main4;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.core.*;

public class ChatClient {
    static void client() {
        Socket socket = null;
        try {
            Fn.writeText("", Const.FILE_NAME_CHAT);
            Fn.writeText("", Const.FILE_NAME_RECEIVE);
            
            socket = new Socket(Mut.serverIp, Integer.parseInt(Mut.port));
            //socket = new Socket("localhost", 7777);
            Print.print("Connected.\n");
            //Mut.connection = true;
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
            
            
            Sender2 sender = new Sender2(socket);
            Receiver2 receiver = new Receiver2(socket);
            
            sender.start();
            receiver.start();
            
        } catch(Exception e) {
            Print.print("Connection failed.\n");
            e.printStackTrace();
        };
        
    }

}