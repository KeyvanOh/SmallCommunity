package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Server extends Thread {
    public Server() {
        super();
    }
    public static void serverMain() {
        System.out.println("Here is serverMain under Server in main1.");
        
        Mut.serverSocket = null;
        try {
            server();
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    private static void server() {
        try {
            Mut.serverSocket = new ServerSocket(Mut.port);
            Print.print("Server is running.\n");
            
            JsonFactory jsonFactory = new JsonFactory();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                new File("port.json"), JsonEncoding.UTF8
            );
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("port", Mut.port);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            
            while(true) {
                Mut.socket = Mut.serverSocket.accept();
                
                Sender sender = new Sender(Mut.socket);
                Receiver receiver = new Receiver(Mut.socket);
                
                sender.start();
                receiver.start();
            }
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        serverMain();
    }
}