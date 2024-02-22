package main2;

import java.io.*;
import java.net.*;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.control.*;
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
    public void start(Stage stage) throws FileNotFoundException {
        System.out.println("Here is start under GUI in main2.");
        
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
        
        TextArea textArea = new TextArea();
        textArea.setLayoutX(0);
        textArea.setLayoutY(height - 30);
        textArea.setPrefWidth(width);
        textArea.setPrefHeight(30);
        
        
        Rectangle rectangleYourIP = new Rectangle();
        rectangleYourIP.setY(7);
        //rectangleYourIP.setWidth(width);
        rectangleYourIP.setWidth(width / 2);
        rectangleYourIP.setHeight(30);
        //rectangleYourIP.setFill(Color.rgb(255, 0, 0));
        rectangleYourIP.setFill(Color.rgb(255, 255, 0));
        rectangleYourIP.setOpacity(0.5);
        
        Rectangle rectangleServerIP = new Rectangle();
        rectangleServerIP.setY(37);
        //rectangleServerIP.setWidth(width);
        rectangleServerIP.setWidth(width / 2);
        rectangleServerIP.setHeight(30);
        //rectangleServerIP.setFill(Color.rgb(0, 0, 255));
        rectangleServerIP.setFill(Color.rgb(0, 255, 0));
        rectangleServerIP.setOpacity(0.5);
        
        Rectangle rectanglePort = new Rectangle();
        rectanglePort.setY(67);
        rectanglePort.setWidth(width / 2);
        rectanglePort.setHeight(30);
        rectanglePort.setFill(Color.rgb(0, 255, 255));
        rectanglePort.setOpacity(0.5);
        
        
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        Text textYourIP = new Text();
        //textYourIP.setText("Your IP: ");
        //textYourIP.setText("Your IP: " + InetAddress.getLocalHost());
        
        String yourIp = "Your IP: ";
        
        try {
            //yourIp.concat(InetAddress.getLocalHost());
            //yourIp += InetAddress.getLocalHost();
            //yourIp += InetAddress.getLocalHost().getHostAddress();
            yourIp = yourIp.concat(InetAddress.getLocalHost().getHostAddress());
        } catch(UnknownHostException e) {
            e.printStackTrace();
        };
        
        textYourIP.setText(yourIp);
        
        textYourIP.setFont(font);
        //textYourIP.setFill(Color.rgb(255, 255, 255));
        textYourIP.setFill(Color.rgb(0, 0, 0));
        //textYourIP.setFill(Color.rgb(255, 255, 0));
        //textYourIP.setX(0);
        textYourIP.setX(10);
        textYourIP.setY(30);
        
        Text textServerIP = new Text();
        textServerIP.setText("Server IP: ");
        textServerIP.setFont(font);
        //textServerIP.setFill(Color.rgb(0, 0, 0));
        //textServerIP.setFill(Color.rgb(255, 255, 255));
        textServerIP.setFill(Color.rgb(0, 0, 0));
        //textServerIP.setFill(Color.rgb(0, 255, 0));
        //textServerIP.setX(0);
        textServerIP.setX(10);
        //textServerIP.setY(30);
        textServerIP.setY(60);
        
        Text textPort = new Text();
        textPort.setText("Port Number: ");
        textPort.setFont(font);
        textPort.setFill(Color.rgb(0, 0, 0));
        textPort.setX(10);
        //textPort.setY(60);
        textPort.setY(90);
        
        
        EventHandler<KeyEvent> eventHandlerTextArea = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println(textArea.getText());
                
                try {
                    JsonFactory jsonFactory = new JsonFactory();
                    
                    JsonGenerator jsonGenerator = jsonFactory.createGenerator(
                        new File("textArea.json"), JsonEncoding.UTF8
                    );
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("text", textArea.getText());
                    jsonGenerator.writeEndObject();
                    jsonGenerator.close();
                } catch(Exception err) {
                    err.printStackTrace();
                };
                
                if (Mut.isOnServerIp == true) {
                    Mut.serverIp = textArea.getText();
                    //textServerIP.setText("Server IP: " + textArea.getText());
                    textServerIP.setText("Server IP: " + Mut.serverIp);
                };
                if (Mut.isOnPort == true) {
                    Mut.port = textArea.getText();
                    //textServerIP.setText("Server IP: " + Mut.serverIp);
                    textPort.setText("Port Number: " + Mut.port);
                };
            }
        };
        textArea.addEventHandler(KeyEvent.KEY_RELEASED, eventHandlerTextArea);
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //rectangleYourIP.setOpacity(0.25);
                //textYourIP.setOpacity(0.25);
                //rectangleYourIP.setOpacity(0.5);
                rectangleYourIP.setOpacity(1.);
                //textYourIP.setOpacity(0.5);
                
                //textYourIP.setText(textYourIP.getText() + textArea.getText());
                //textYourIP.setText("Your IP: " + textArea.getText());
            }
        };
        rectangleYourIP.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerMouseOnYourIp);
        
        EventHandler<MouseEvent> eventHandlerMouseOutYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //rectangleYourIP.setOpacity(0.25);
                rectangleYourIP.setOpacity(0.5);
                //rectangleYourIP.setOpacity(1.);
                //textYourIP.setOpacity(0.5);
                //textYourIP.setOpacity(1.);
                //textYourIP.setText("Your IP: " + textArea.getText());
            }
        };
        rectangleYourIP.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutYourIp);
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnServerIP = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnServerIp = true;
                //rectangleServerIP.setOpacity(0.5);
                rectangleServerIP.setOpacity(1.);
                //textServerIP.setOpacity(0.5);
                //textServerIP.setText("Server IP: " + textArea.getText());
                textArea.clear();
            }
        };
        rectangleServerIP.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerMouseOnServerIP);
        
        EventHandler<MouseEvent> eventHandlerMouseOutServerIP = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnServerIp = false;
                //rectangleServerIP.setOpacity(1.);
                rectangleServerIP.setOpacity(0.5);
                //textServerIP.setOpacity(1.);
                //textServerIP.setText("Server IP: " + textArea.getText());
            }
        };
        rectangleServerIP.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutServerIP);
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnPort = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnPort = true;
                rectanglePort.setOpacity(1.);
            }
        };
        rectanglePort.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerMouseOnPort);
        
        EventHandler<MouseEvent> eventHandlerMouseOutPort = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnPort = false;
                rectanglePort.setOpacity(0.5);
            }
        };
        rectanglePort.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutPort);
        
        
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textArea);
        root.getChildren().add(rectangleYourIP);
        root.getChildren().add(rectangleServerIP);
        root.getChildren().add(rectanglePort);
        root.getChildren().add(textYourIP);
        root.getChildren().add(textServerIP);
        root.getChildren().add(textPort);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}