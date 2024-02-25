package main1;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.*;

public class Sender999 extends Thread {
    Socket socket;
    //Socket socket = new Socket(Mut.serverIp, Integer.parseInt(Mut.port));;
    DataOutputStream out;
    
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    JsonParser jsonParser;
    
    String postNumberDelete;
    
    //public Sender(Socket socket) {
    //public Sender999(String serverIp) {
    public Sender999(String serverIp, String postNumberDelete) {
        super();
        //this.socket = socket;
        //this.socket = new Socket(Mut.serverIp, Integer.parseInt(Mut.port));;
        this.postNumberDelete = postNumberDelete;
        try {
            socket = new Socket(serverIp, 999);
            out = new DataOutputStream(socket.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //JsonParser jsonParser;
        //while (out != null) {
        if (out != null) {
            try {
                jsonGenerator = jsonFactory.createJsonGenerator(
                    out, JsonEncoding.UTF8
                );
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("postNumberDelete", postNumberDelete);
                jsonGenerator.flush();
                //jsonGenerator.close();
                System.out.println("Sent postNumberDelete to delete");
            } catch(IOException e) {
                e.printStackTrace();
            };
            
        };
    }
}