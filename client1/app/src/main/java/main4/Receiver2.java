package main4;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.core.*;

public class Receiver2 extends Thread {
    Socket socket;
    DataInputStream in;
    public Receiver2(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(in != null) {
            try {
                //String receive = in.readUTF();
                
                /*
                Mut.chatStack = Fn.readText(Const.FILE_NAME_RECEIVE);
                Mut.chatStack = Mut.chatStack.concat(receive).concat("\n");
                
                System.out.println(Mut.chatStack);
                
                Fn.writeText(Mut.chatStack, Const.FILE_NAME_RECEIVE);
                */
                
                /*
                StringBuilder print = new StringBuilder();
                print.append(Fn.readText(Const.FILE_NAME_RECEIVE));
                print.append(receive);
                print.append("\n");
                System.out.println(print);
                Fn.writeText(print.toString(), Const.FILE_NAME_RECEIVE);
                */
                
                
                JsonFactory jsonFactory = new JsonFactory();
                JsonParser jsonParser = jsonFactory.createJsonParser(in);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
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
                };
                
                
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver2 err\n");
                break;
            };
            
        };
    }
}