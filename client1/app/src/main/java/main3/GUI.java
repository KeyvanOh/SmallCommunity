package main3;

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

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        System.out.println("Here is start under GUI in main3.");
        
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
        
        Rectangle rectangleYourIp = new Rectangle();
        rectangleYourIp.setY(7);
        rectangleYourIp.setWidth(width / 2);
        rectangleYourIp.setHeight(30);
        rectangleYourIp.setFill(Color.rgb(255, 255, 0));
        rectangleYourIp.setOpacity(0.5);
        
        Rectangle rectangleServerIp = new Rectangle();
        rectangleServerIp.setY(37);
        rectangleServerIp.setWidth(width / 2);
        rectangleServerIp.setHeight(30);
        rectangleServerIp.setFill(Color.rgb(0, 255, 0));
        rectangleServerIp.setOpacity(0.5);
        
        Rectangle rectanglePort = new Rectangle();
        rectanglePort.setY(67);
        rectanglePort.setWidth(width / 2);
        rectanglePort.setHeight(30);
        rectanglePort.setFill(Color.rgb(0, 255, 255));
        rectanglePort.setOpacity(0.5);
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        Text textYourIp = new Text();
        
        String yourIp = "Your IP: ";
        try {
            yourIp = yourIp.concat(InetAddress.getLocalHost().getHostAddress());
        } catch(UnknownHostException e) {
            e.printStackTrace();
        };
        textYourIp.setText(yourIp);
        textYourIp.setFont(font);
        textYourIp.setFill(Color.rgb(0, 0, 0));
        textYourIp.setX(10);
        textYourIp.setY(30);
        
        Text textServerIp = new Text();
        textServerIp.setText("Server IP: ");
        textServerIp.setFont(font);
        textServerIp.setFill(Color.rgb(0, 0, 0));
        textServerIp.setX(10);
        textServerIp.setY(60);
        
        Text textPort = new Text();
        textPort.setText("Port Number: ");
        textPort.setFont(font);
        textPort.setFill(Color.rgb(0, 0, 0));
        textPort.setX(10);
        textPort.setY(90);
        
        EventHandler<KeyEvent> eventHandlerTextArea = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
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
                //if (Mut.isOnServerIp == true) {
                //if (Mut.isOnServerIp == true ) {
                if (Mut.buttonClicked == 2 ) {
                    Mut.serverIp = textArea.getText();
                    textServerIp.setText("Server IP: " + Mut.serverIp);
                } else if (Mut.buttonClicked == 3) {
                //if (Mut.isOnPort == true) {
                    Mut.port = textArea.getText();
                    textPort.setText("Port Number: " + Mut.port);
                };
            }
        };
        textArea.addEventHandler(KeyEvent.KEY_RELEASED, eventHandlerTextArea);
        
        /*
        EventHandler<MouseEvent> eventHandlerMouseOnYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                rectangleYourIp.setOpacity(1.);
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerMouseOnYourIp);
        EventHandler<MouseEvent> eventHandlerMouseOutYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                rectangleYourIp.setOpacity(0.5);
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutYourIp);
        */
        
        
        
        
        
        
        EventHandler<MouseEvent> eventHandlerMouseOutYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //rectangleYourIp.setOpacity(0.5);
                //rectangleYourIp.setOpacity(1.);
                
                //System.out.println(e);
                //System.out.println(e.getEventType());
                
                //MouseEvent mouseEvent = e.getEventType();
                //MouseEvent mouseEvent = e.getEventType();
                //String mouseEvent = e.getEventType();
                String mouseEvent = String.valueOf(e.getEventType());
                //System.out.println(mouseEvent);
                
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        rectangleYourIp.setOpacity(1.);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 1) {
                            rectangleYourIp.setOpacity(0.5);
                        };
                        //rectangleYourIp.setOpacity(0.5);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        //System.out.println(e.getButton());
                        //if (e.getButton() == MouseEvent.MouseButton.PRIMARY) {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 1;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(1.);
                            rectangleServerIp.setOpacity(0.5);
                            rectanglePort.setOpacity(0.5);
                            //Fn.setRectOpacity();
                        };
                        
                        break;
                    }
                    default: {
                    }
                };
                
                /*
                if (rectangleYourIp.getOpacity() == 0.5) {
                    rectangleYourIp.setOpacity(1.);
                } else {
                    rectangleYourIp.setOpacity(0.5);
                };
                */
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOutYourIp);
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnServerIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        Mut.isOnServerIp = true;
                        //textArea.clear();
                        rectangleServerIp.setOpacity(1.);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 2) {
                            rectangleServerIp.setOpacity(0.5);
                        };
                        Mut.isOnServerIp = false;
                        //rectangleServerIp.setOpacity(0.5);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 2;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(0.5);
                            rectangleServerIp.setOpacity(1.);
                            rectanglePort.setOpacity(0.5);
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        rectangleServerIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnServerIp);
        
        
        
        
        
        
        
        
        
        /*
        EventHandler<MouseEvent> eventHandlerMouseOnServerIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnServerIp = true;
                rectangleServerIp.setOpacity(1.);
                textArea.clear();
            }
        };
        rectangleServerIp.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandlerMouseOnServerIp);
        EventHandler<MouseEvent> eventHandlerMouseOutServerIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Mut.isOnServerIp = false;
                rectangleServerIp.setOpacity(0.5);
            }
        };
        rectangleServerIp.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutServerIp);
        */
        
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnPort = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        Mut.isOnPort = true;
                        //textArea.clear();
                        rectanglePort.setOpacity(1.);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 3) {
                            rectanglePort.setOpacity(0.5);
                        };
                        Mut.isOnPort = false;
                        //rectanglePort.setOpacity(0.5);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 3;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(0.5);
                            rectangleServerIp.setOpacity(0.5);
                            rectanglePort.setOpacity(1.);
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        rectanglePort.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnPort);
        
        
        
        /*
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
        */
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textArea);
        root.getChildren().add(rectangleYourIp);
        root.getChildren().add(rectangleServerIp);
        root.getChildren().add(rectanglePort);
        root.getChildren().add(textYourIp);
        root.getChildren().add(textServerIp);
        root.getChildren().add(textPort);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}