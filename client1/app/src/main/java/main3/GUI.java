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
        //rectangleYourIp.setFill(Color.rgb(255, 255, 0));
        rectangleYourIp.setFill(Color.rgb(0, 255, 0));
        rectangleYourIp.setOpacity(Const.A_SHADOW);
        
        Rectangle rectangleServerIp = new Rectangle();
        rectangleServerIp.setY(37);
        rectangleServerIp.setWidth(width / 2);
        rectangleServerIp.setHeight(30);
        //rectangleServerIp.setFill(Color.rgb(0, 255, 0));
        rectangleServerIp.setFill(Color.rgb(255, 255, 255));
        rectangleServerIp.setOpacity(Const.A_SHADOW);
        
        Rectangle rectanglePort = new Rectangle();
        rectanglePort.setY(67);
        rectanglePort.setWidth(width / 2);
        rectanglePort.setHeight(30);
        //rectanglePort.setFill(Color.rgb(0, 255, 255));
        rectanglePort.setFill(Const.RED);
        rectanglePort.setOpacity(Const.A_SHADOW);
        
        
        
        
        
        
        
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
        textYourIp.setFill(Const.BLACK);
        textYourIp.setX(10);
        textYourIp.setY(30);
        
        Text textServerIp = new Text();
        textServerIp.setText("Server IP: ");
        textServerIp.setFont(font);
        textServerIp.setFill(Const.BLACK);
        textServerIp.setX(10);
        textServerIp.setY(60);
        
        Text textPort = new Text();
        textPort.setText("Port Number: ");
        textPort.setFont(font);
        textPort.setFill(Const.BLACK);
        textPort.setX(10);
        textPort.setY(90);
        
        
        Text textConnection = new Text();
        textConnection.setText("NULL");
        textConnection.setFont(font);
        textConnection.setFill(Const.RED);
        textConnection.setX(width - 100);
        textConnection.setY(30);
        
        
        
        
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
                rectangleYourIp.setOpacity(Const.A_SHADOW);
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutYourIp);
        */
        
        
        
        
        
        
        EventHandler<MouseEvent> eventHandlerMouseOutYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //rectangleYourIp.setOpacity(Const.A_SHADOW);
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
                        //rectangleYourIp.setOpacity(1.);
                        rectangleYourIp.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 1) {
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                        };
                        //rectangleYourIp.setOpacity(Const.A_SHADOW);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        //System.out.println(e.getButton());
                        //if (e.getButton() == MouseEvent.MouseButton.PRIMARY) {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 1;
                            System.out.println(Mut.buttonClicked);
                            //rectangleYourIp.setOpacity(1.);
                            rectangleYourIp.setOpacity(Const.A_MAX);
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                            rectanglePort.setOpacity(Const.A_SHADOW);
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
                    rectangleYourIp.setOpacity(Const.A_SHADOW);
                };
                */
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOutYourIp);
        textYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOutYourIp);
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnServerIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        Mut.isOnServerIp = true;
                        //textArea.clear();
                        //rectangleServerIp.setOpacity(1.);
                        rectangleServerIp.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 2) {
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                        };
                        Mut.isOnServerIp = false;
                        //rectangleServerIp.setOpacity(Const.A_SHADOW);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 2;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                            //rectangleServerIp.setOpacity(1.);
                            rectangleServerIp.setOpacity(Const.A_MAX);
                            rectanglePort.setOpacity(Const.A_SHADOW);
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        rectangleServerIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnServerIp);
        textServerIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnServerIp);
        
        
        
        
        
        
        
        
        
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
                rectangleServerIp.setOpacity(Const.A_SHADOW);
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
                        //rectanglePort.setOpacity(1.);
                        rectanglePort.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 3) {
                            rectanglePort.setOpacity(Const.A_SHADOW);
                        };
                        Mut.isOnPort = false;
                        //rectanglePort.setOpacity(Const.A_SHADOW);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 3;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                            //rectanglePort.setOpacity(1.);
                            rectanglePort.setOpacity(Const.A_MAX);
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        rectanglePort.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnPort);
        textPort.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnPort);
        
        
        
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
                rectanglePort.setOpacity(Const.A_SHADOW);
            }
        };
        rectanglePort.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerMouseOutPort);
        */
        
        
        /*
        EventHandler<MouseEvent> eventHandlerMouseOnConnectionButton = new EventHandler<MouseButton>() {
            @Override
            public void handle(MouseEvent e) {
                textConnection.setFill()
            }
            
        };
        */
        
        EventHandler<MouseEvent> eventHandlerMouseOnConnectionButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        //Mut.isOnPort = true;
                        //textArea.clear();
                        //rectanglePort.setOpacity(1.);
                        //rectanglePort.setOpacity(Const.A_MAX);
                        textConnection.setFill(Const.YELLOW);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        /*
                        if (Mut.buttonClicked != 3) {
                            rectanglePort.setOpacity(Const.A_SHADOW);
                        };
                        Mut.isOnPort = false;
                        */
                        //rectanglePort.setOpacity(Const.A_SHADOW);
                        textConnection.setFill(Const.RED);
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            /*
                            textArea.clear();
                            Mut.buttonClicked = 3;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                            //rectanglePort.setOpacity(1.);
                            rectanglePort.setOpacity(Const.A_MAX);
                            */
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        textConnection.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnConnectionButton);
        
        
        
        EventHandler<MouseEvent> eventHandlerMouseOnBackground = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                /*
                String mouseEvent = String.valueOf(e.getEventType());
                if (mouseEvent.equals("MOUSE_CLICKED")) {
                    textArea.clear();
                    Mut.buttonClicked = 0;
                    System.out.println(Mut.buttonClicked);
                    rectangleYourIp.setOpacity(Const.A_SHADOW);
                    rectangleServerIp.setOpacity(Const.A_SHADOW);
                    rectanglePort.setOpacity(Const.A_SHADOW);
                };
                */
                textArea.clear();
                Mut.buttonClicked = 0;
                System.out.println(Mut.buttonClicked);
                rectangleYourIp.setOpacity(Const.A_SHADOW);
                rectangleServerIp.setOpacity(Const.A_SHADOW);
                rectanglePort.setOpacity(Const.A_SHADOW);
            }
        };
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerMouseOnBackground);
        
        
        
        
        
        
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textArea);
        root.getChildren().add(rectangleYourIp);
        root.getChildren().add(rectangleServerIp);
        root.getChildren().add(rectanglePort);
        root.getChildren().add(textYourIp);
        root.getChildren().add(textServerIp);
        root.getChildren().add(textPort);
        root.getChildren().add(textConnection);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}