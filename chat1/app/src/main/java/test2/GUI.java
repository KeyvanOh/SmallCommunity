package test2;

import java.io.*;
import java.net.*;
//import java.util.concurrent.ThreadLocalRandom;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException, UnknownHostException {
        System.out.println("Here is start under GUI in test2.");
        
        InetAddress ip = InetAddress.getLocalHost();
        
        String ipv4 = ip.getHostAddress();
        
        //int portRand = ThreadLocalRandom.current().nextInt(0, 65535 + 1);
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        Image image = new Image(new FileInputStream("bg.png"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        PixelReader pixelReader = image.getPixelReader();
        
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter writer = wImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = pixelReader.getColor(x, y);
                writer.setColor(x, y, c);
            };
        };
        ImageView imageView = new ImageView(wImage);
        
        Print.append("IPv4: ");
        Print.append(ipv4);
        Print.append("\nPort Number: ");
        //Print.append(portRand);
        int port = 0;
        //Print.append(Mut.port);
        Print.append(port);
        
        
        
        
        Text text = new Text();
        text.setText(Print.getAndReset());
        text.setFont(font);
        text.setFill(Color.rgb(0, 255, 0));
        text.setX(0);
        text.setY(32);
        
        
        
        
        
        Text buttonShow = new Text();
        buttonShow.setText("SHOW");
        buttonShow.setFont(font);
        buttonShow.setFill(Color.rgb(255, 0, 0));
        buttonShow.setX(0);
        buttonShow.setY(64);
        
        
        EventHandler<MouseEvent> eventHandlerText1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //MouseButton mouseButton = e.getButton();
                //switch()
                buttonShow.setFill(Color.rgb(0, 0, 255));
                //System.out.println("MOUSE_ENTERED");
                //System.out.println("MOUSE_ENTERED");
                
                
                //System.out.println(e);
                
                /*
                switch(e) {
                    case MouseEvent.MOUSE_ENTERED: {
                        break;
                    }
                    default: {
                    }
                }
                */
                
            }
        };
        buttonShow.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerText1);
        
        EventHandler<MouseEvent> eventHandlerText2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                buttonShow.setFill(Color.rgb(255, 0, 0));
                //System.out.println("MOUSE_EXITED");
            }
        };
        buttonShow.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerText2);
        //buttonShow.addEventHandler(MouseEvent.MOUSE_MOVED, eventHandlerText2);
        
        EventHandler<MouseEvent> eventHandlerText3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //buttonShow.setFill(Color.rgb(255, 0, 0));
                //System.out.println();
                
                
                try {
                    JsonFactory jsonFactory = new JsonFactory();
                    
                    //JsonGenerator jsonGenerator =
                    
                    
                    
                    
                    
                    JsonParser jsonParser = jsonFactory.createParser(new File("port.json"));
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = jsonParser.getCurrentName();
                        if ("port".equals(fieldName)) {
                            jsonParser.nextToken();
                            System.out.println(jsonParser.getText());
                            
                            
                            Print.append("IPv4: ");
                            Print.append(ipv4);
                            Print.append("\nPort Number: ");
                            //Print.append(portRand);
                            //int port = 0;
                            //port = jsonParser.getText();
                            //port = Integer.parseInt(jsonParser.getText());
                            int port = Integer.parseInt(jsonParser.getText());
                            //Print.append(Mut.port);
                            Print.append(port);
                            
                            text.setText(Print.getAndReset());
                            
                        };
                    };
                //} catch(Exception e) {
                } catch(IOException err) {
                    err.printStackTrace();
                };
                
                
                
                
                
                
                
                
                
                
                
                
                
            }
        };
        buttonShow.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerText3);
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        root.getChildren().add(buttonShow);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("server");
        stage.setScene(scene);
        stage.show();
    }
}