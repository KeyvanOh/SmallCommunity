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
            //Thread.sleep(1000);
            try {
                Thread.sleep(1000);
                if (Mut.request == true) {
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", "123");
                    jsonGenerator.writeStringField("serverIp", "456");
                    jsonGenerator.writeStringField("port", "789");
                    jsonGenerator.writeEndObject();
                    jsonGenerator.flush();
                    
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