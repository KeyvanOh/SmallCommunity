package main1;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while (in != null) {
            //Thread.sleep(1000);
            try {
                Thread.sleep(1000);
                JsonFactory jsonFactory = new JsonFactory();
                JsonParser jsonParser = jsonFactory.createJsonParser(in);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    if (fieldName != null) {
                        switch (fieldName) {
                            case "userIp": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                                break;
                            }
                            case "serverIp": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                                break;
                            }
                            case "port": {
                                jsonParser.nextToken();
                                System.out.println(jsonParser.getText());
                            }
                            default: {
                            }
                        };
                    };
                };
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}