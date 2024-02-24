package test3;

import java.net.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Server extends Thread {
    public Server() {
        super();
    }
    public static void serverMain() {
        System.out.println("Here is Server in test3.");
        
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
            try {
                JsonFactory jsonFactory = new JsonFactory();
                
                JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                    new File("port.json"), JsonEncoding.UTF8
                );
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("port", Mut.port);
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch(Exception e) {
                e.printStackTrace();
            };
            
            while(true) {
                Mut.socket = Mut.serverSocket.accept();
                
                Sender sender = new Sender(Mut.socket);
                Receiver receiver = new Receiver(Mut.socket);
                
                sender.start();
                receiver.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        serverMain();
    }
}
class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    //String name;
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonGenerator;
    
    public Sender(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            //name = "[".concat(String.valueOf(socket.getInetAddress())).concat(String.valueOf(socket.getPort())).concat("]");
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //Scanner scanner = new Scanner(System.in, "Cp949");
        //String temp = new String();
        //String input = "WELCOME.";
        while (out != null) {
            try {
                //out.writeUTF(name + scanner.nextLine());
                //out.writeUTF(name);
                //out.writeUTF("WELCOME.");
                //System.out.println(out);
                
                //String input = "WELCOME.";
                
                //if (temp.equals("WELCOME.") == false) {
                
                /*
                if (temp.equals(input) == false) {
                    //temp = "WELCOME.";
                    temp = input;
                    out.writeUTF(temp);
                };
                */
                
                
                
                /*
                if (temp.equals(input) == false) {
                    temp = input;
                    
                    JsonFactory jsonFactory = new JsonFactory();
                    JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", "123");
                    jsonGenerator.writeStringField("serverIp", "456");
                    jsonGenerator.writeStringField("port", "789");
                    jsonGenerator.writeEndObject();
                    //jsonGenerator.close();
                };                    
                */

                
                
                
                if (Mut.request == true) {
                    //JsonFactory jsonFactory = new JsonFactory();
                    //JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(
                    jsonGenerator = jsonFactory.createJsonGenerator(
                        out, JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("userIp", "123");
                    jsonGenerator.writeStringField("serverIp", "456");
                    jsonGenerator.writeStringField("port", "789");
                    jsonGenerator.writeEndObject();
                    //jsonGenerator.close();
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
class Receiver extends Thread {
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
            try {
                //System.out.println(in.readUTF());
                //System.out.println(in);
                
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
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}