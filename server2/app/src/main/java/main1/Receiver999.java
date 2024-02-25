package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Receiver999 extends Thread {
    Socket socket;
    //Mut.socket = Mut.serverSocket.accept();
    //Socket socket = new ServerSocket(999);
    //Socket socket;
    DataInputStream in;
    JsonParser jsonParser;
    JsonFactory jsonFactory = new JsonFactory();
    //public Receiver999() {
    public Receiver999(Socket socket) {
        this.socket = socket;
        try {
            //socket = new ServerSocket(999).accept();
            //in = new DataInputStream(socket.getInputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while (in != null) {
            //System.out.println("Thread Receiver of server2 is ALIVE.");
            //Thread.sleep(1000);
            try {
                Thread.sleep(1000);
                
                //jsonParser = jsonFactory.createJsonParser(new File("request.json"));
                jsonParser = jsonFactory.createJsonParser(in);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if ("postNumberDelete".equals(jsonParser.getCurrentName())) {
                        jsonParser.nextToken();
                        //request = jsonParser.getBooleanValue();
                        
                        
                        File postToDelete = new File("posts/" + jsonParser.getText() + ".json"); 
                        if (postToDelete.delete()) {
                            System.out.println("Successfully deleted " + postToDelete.getName());
                        } else {
                            System.out.println("Failed to delete.");
                        };
                        System.out.println("Tried to delete at least.");
                        
                        //System.out.println("postNumberDelete: " + jsonParser.getText());
                    };
                };
                
                
                
            } catch(Exception e) {
                e.printStackTrace();
                //Print.print("Receiver err\n");
                
                
                
                
                
                
                
                break;
            };
            
            
        };
    }

}
