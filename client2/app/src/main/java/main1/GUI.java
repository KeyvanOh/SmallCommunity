package main1;

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

//import javafx.fxd.Duplicator;
//import javafx.fxd.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        System.out.println("Here is start under GUI in main1.");
        
        Group root = new Group();
        //Group root2 = new Group();
        //Scene scene = new Scene(root, width, height);
        
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator;
        try {
            jsonGenerator = jsonFactory.createGenerator(
                new File("connection.json"), JsonEncoding.UTF8
            );
            jsonGenerator.writeStartObject();
            jsonGenerator.writeBooleanField("connection", false);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            
            jsonGenerator = jsonFactory.createGenerator(
                new File("request.json"), JsonEncoding.UTF8
            );
            jsonGenerator.writeStartObject();
            jsonGenerator.writeBooleanField("request", false);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch(Exception e) {
            e.printStackTrace();
        };
        
        Image image = new Image(new FileInputStream("bg.png"));
        //int width = (int)image.getWidth();
        //int height = (int)image.getHeight();
        double width = image.getWidth();
        double height = image.getHeight();
        
        Scene scene = new Scene(root, width, height);
        //Scene scene2 = new Scene(root2, width, height);
        
        PixelReader pixelReader = image.getPixelReader();
        
        //WritableImage wImage = new WritableImage(width, height);
        WritableImage wImage = new WritableImage((int)width, (int)height);
        PixelWriter writer = wImage.getPixelWriter();
        //for (int y = 0; y < height; y++) {
        //    for (int x = 0;  x < width; x++) {
        for (int y = 0; y < (int)height; y++) {
            for (int x = 0;  x < (int)width; x++) {
                Color c = pixelReader.getColor(x, y);
                writer.setColor(x, y, c);
            }
        };
        ImageView imageView = new ImageView(wImage);
        
        TextArea textArea = new TextArea();
        //textArea.setLayoutX(0);
        //textArea.setLayoutY(height - 30);
        textArea.setLayoutX(- width);
        textArea.setLayoutY(- height);
        //textArea.setPrefWidth(width);
        //textArea.setPrefHeight(30);
        textArea.setPrefWidth(0);
        textArea.setPrefHeight(0);
        //textArea.setPrefWidth(- width);
        //textArea.setPrefHeight(- height);
        textArea.setOpacity(0.);
        
        //Rectangle rectangleYourIp = new Rectangle();
        //Rectangle(double width, double height, Paint fill)
        Rectangle rectangleYourIp = new Rectangle((double)width * 0.5, 30., Color.rgb(0, 255, 0));
        rectangleYourIp.setY(7);
        rectangleYourIp.setOpacity(Const.A_SHADOW);
        
        Rectangle rectangleServerIp = new Rectangle((double)width * 0.5, 30., Const.WHITE);
        rectangleServerIp.setY(37);
        rectangleServerIp.setOpacity(Const.A_SHADOW);
        
        Rectangle rectanglePort = new Rectangle((double)width * 0.5, 30., Const.RED);
        rectanglePort.setY(67);
        rectanglePort.setOpacity(Const.A_SHADOW);
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        //Text(double x, double y, String text)
        //Text textYourIp = new Text();
        String yourIp = "Your IP: ";
        try {
            Mut.yourIp = InetAddress.getLocalHost().getHostAddress();
            yourIp = yourIp.concat(Mut.yourIp);
        } catch(UnknownHostException e) {
            e.printStackTrace();
        };
        Text textYourIp = new Text(10., 30., yourIp);
        textYourIp.setFont(font);
        textYourIp.setFill(Const.BLACK);
        
        Text textServerIp = new Text(10., 60., "Server IP: ");
        textServerIp.setFont(font);
        textServerIp.setFill(Const.BLACK);
        
        Text textPort = new Text(10., 90., "Port Number: ");
        textPort.setFont(font);
        textPort.setFill(Const.BLACK);
        
        //Text textConnection = new Text(width - 100., 30., "NULL");
        Text textConnection = new Text(width - 100., 30., "CONNECT");
        textConnection.setFont(font);
        textConnection.setFill(Const.RED);
        
        
        
        
        
        //Rectangle rectangleServerIp = new Rectangle((double)width * 0.5, 30., Const.WHITE);
        //rectangleServerIp.setY(37);
        //rectangleServerIp.setOpacity(Const.A_SHADOW);
        
        //Rectangle rectangleScreen = new Rectangle((double)width * 0.5, height - 100., Const.WHITE);
        //Rectangle rectangleScreen = new Rectangle((double)width * 0.5, 30., Const.WHITE);
        //Rectangle rectangleScreen = new Rectangle((double)width * 0.5, 100., Const.WHITE);
        //Rectangle rectangleScreen = new Rectangle(width * 0.5, height - 100., Const.WHITE);
        //Rectangle rectangleScreen = new Rectangle(width * 0.5, height - 30., Const.WHITE);
        Rectangle rectangleScreen = new Rectangle(width * 0.5, height - 37., Const.WHITE);
        rectangleScreen.setY(37);
        //rectangleScreen.setY(height);
        //rectangleScreen.setY(40.);
        rectangleScreen.setOpacity(Const.A_SHADOW);
        rectangleScreen.setVisible(false);
        
        Rectangle rectangleIndex = new Rectangle(width * 0.5, height - 37., Const.RED);
        rectangleIndex.setX(width * 0.5);
        rectangleIndex.setY(37);
        rectangleIndex.setOpacity(Const.A_SHADOW);
        rectangleIndex.setVisible(false);
        
        Text textPosting = new Text(10., 60., "");
        textPosting.setFont(font);
        textPosting.setFill(Const.BLACK);
        textPosting.setVisible(false);
        
        
        
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
                if (Mut.buttonClicked == 2) {
                    Mut.serverIp = textArea.getText().replaceAll("[^0-9.]", "");
                    textServerIp.setText("Server IP: " + Mut.serverIp);
                } else if (Mut.buttonClicked == 3) {
                    Mut.port = textArea.getText().replaceAll("[^\\d]", "");
                    textPort.setText("Port Number: " + Mut.port);
                } else if (Mut.buttonClicked == 1 && Mut.connection == true) {
                    Mut.posting = textArea.getText();
                    textPosting.setText(Mut.posting);
                    
                    
                };
            }
        };
        textArea.addEventHandler(KeyEvent.KEY_RELEASED, eventHandlerTextArea);
        
        EventHandler<MouseEvent> eventHandlerMouseOnYourIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        rectangleYourIp.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 1) {
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                        };
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        //textConnection.setVisible(true);
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 1;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_MAX);
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                            rectanglePort.setOpacity(Const.A_SHADOW);
                            textPosting.setText("Write whatevet to post here.");
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        rectangleYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnYourIp);
        textYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnYourIp);
        
        EventHandler<MouseEvent> eventHandlerMouseOnServerIp = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        Mut.isOnServerIp = true;
                        rectangleServerIp.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 2) {
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
                        };
                        Mut.isOnServerIp = false;
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 2;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
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
        
        EventHandler<MouseEvent> eventHandlerMouseOnPort = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        Mut.isOnPort = true;
                        rectanglePort.setOpacity(Const.A_MAX);
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.buttonClicked != 3) {
                            rectanglePort.setOpacity(Const.A_SHADOW);
                        };
                        Mut.isOnPort = false;
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            textArea.clear();
                            Mut.buttonClicked = 3;
                            System.out.println(Mut.buttonClicked);
                            rectangleYourIp.setOpacity(Const.A_SHADOW);
                            rectangleServerIp.setOpacity(Const.A_SHADOW);
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
        
        EventHandler<MouseEvent> eventHandlerMouseOnConnectionButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        if (Mut.connection == false) {
                            textConnection.setFill(Const.YELLOW);
                        } else {
                            textConnection.setFill(Const.RED);
                        };
                        break;
                    }
                    case "MOUSE_EXITED": {
                        if (Mut.connection == false) {
                            textConnection.setFill(Const.RED);
                        } else {
                            textConnection.setFill(Const.YELLOW);
                        };
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            if (Mut.connection == false) {
                                System.out.println("Clicking NULL");
                                ChatClient.client();
                            } else {
                                System.out.println("Clicking CONNECTED");
                                
                                JsonFactory jsonFactory = new JsonFactory();
                                JsonGenerator jsonGenerator;
                                try {
                                    jsonGenerator = jsonFactory.createGenerator(
                                        new File("request.json"), JsonEncoding.UTF8
                                    );
                                    jsonGenerator.writeStartObject();
                                    jsonGenerator.writeBooleanField("request", true);
                                    jsonGenerator.writeEndObject();
                                    jsonGenerator.close();
                                    
                                    
                                    if (Mut.buttonClicked == 1) {
                                        
                                        
                                        System.out.println(Mut.yourIp);
                                        System.out.println(String.valueOf(new java.util.Date()));
                                        System.out.println(Const.idTime);
                                        System.out.println(Mut.posting);
                                        textArea.clear();
                                        //Mut.posting = "";
                                        //textPosting.setText("");
                                        //textPosting.setText(textArea.getText());
                                        //textPosting.setText("You posted.");
                                        textPosting.setText("You posted.\nClick your IP to post more.");
                                        Mut.buttonClicked = 0;
                                        rectangleYourIp.setOpacity(Const.A_SHADOW);
                                        
                                        
                                        
                                    };
                                    
                                    
                                    
                                    
                                } catch(Exception err) {
                                    err.printStackTrace();
                                };
                            };
                            //root.getChildren().remove(textConnection);
                            /*
                            if (stage.getScene() == scene) {
                                stage.setScene(scene2);
                            } else {
                                stage.setScene(scene);
                            };
                            //stage.setScene(scene2);
                            */
                            //System.out.println(stage.getScene());
                            //textConnection.setVisible(false);
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
                textArea.clear();
                Mut.buttonClicked = 0;
                System.out.println(Mut.buttonClicked);
                rectangleYourIp.setOpacity(Const.A_SHADOW);
                rectangleServerIp.setOpacity(Const.A_SHADOW);
                rectanglePort.setOpacity(Const.A_SHADOW);
            }
        };
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerMouseOnBackground);
        
        EventHandler<MouseEvent> eventHandlerMouseOnWherever = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //System.out.println("event form root.");
                try {
                    JsonFactory jsonFactory = new JsonFactory();
                    JsonParser jsonParser = jsonFactory.createJsonParser(new File("connection.json"));
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        if ("connection".equals(jsonParser.getCurrentName())) {
                            jsonParser.nextToken();
                            Mut.connection = jsonParser.getBooleanValue();
                        };
                    };
                } catch(Exception err) {
                    err.printStackTrace();
                };
                
                if (Mut.connection == true) {
                    /*
                    textConnection.setText("CONNECTED");
                    //root.getChildren().remove(textConnection);
                    rectangleYourIp.setVisible(false);
                    */
                    if (Mut.connectionFirst == false) {
                        //textConnection.setText("CONNECTED");
                        textConnection.setText("POST");
                        //root.getChildren().remove(textConnection);
                        rectangleServerIp.setVisible(false);
                        rectanglePort.setVisible(false);
                        textServerIp.setVisible(false);
                        textPort.setVisible(false);
                        
                        rectangleScreen.setVisible(true);
                        rectangleIndex.setVisible(true);
                        textPosting.setVisible(true);
                        
                        Mut.connectionFirst = true;
                        
                        textPosting.setText("Click your IP to post.");
                    };
                    
                };
                //writer.setColor(100, 100, Const.WHITE);
                //writer.setColor(e.getX(), e.getY(), Const.WHITE);
                //writer.setColor((int)e.getX(), (int)e.getY(), Const.WHITE);
                
                int x = (int)e.getX();
                int y = (int)e.getY();
                if (x > 0 && x < width && y > 0 && y < height) {
                    writer.setColor((int)e.getX(), (int)e.getY(), Const.WHITE);
                };
                
                //if 
                //System.out.println(e);
                //System.out.println(new java.util.Date());
                /*
                System.out.println(Mut.yourIp);
                System.out.println(String.valueOf(new java.util.Date()));
                //System.out.println(Mut.);
                //System.out.println(System.currentTimeMillis());
                System.out.println(Const.idTime);
                System.out.println(Mut.posting);
                */
            }
        };
        /*
        imageView.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        textArea.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        rectangleYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        rectangleServerIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        rectanglePort.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        textYourIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        textServerIp.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        textPort.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        textConnection.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        */
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textArea);
        root.getChildren().add(rectangleYourIp);
        root.getChildren().add(rectangleServerIp);
        root.getChildren().add(rectanglePort);
        root.getChildren().add(textYourIp);
        root.getChildren().add(textServerIp);
        root.getChildren().add(textPort);
        root.getChildren().add(textConnection);
        
        root.getChildren().add(rectangleScreen);
        root.getChildren().add(rectangleIndex);
        root.getChildren().add(textPosting);
        
        root.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        
        //Scene scene = new Scene(root, width, height);
        
        //Group root2 = new Group();
        //root2.getChildren().add(imageView);
        //root2.getChildren().add(textArea);
        //root2.getChildren().add(rectangleYourIp);
        //root2.getChildren().add(rectangleServerIp);
        //root2.getChildren().add(rectanglePort);
        //root2.getChildren().add(textYourIp);
        //root2.getChildren().add(textServerIp);
        //root2.getChildren().add(textPort);
        //root2.getChildren().add(textConnection);
        //root2.addEventHandler(MouseEvent.ANY, eventHandlerMouseOnWherever);
        
        
        //ImageView imageView2 = imageView.clone();
        //ImageView imageView2 = imageView;
        //ImageView imageView2 = new ImageView();
        //imageView2 = imageView.clone();
        //ImageView imageView2 = Duplicator.duplicate();
        /*
        TextArea textArea2 = new TextArea();
        textArea2.setLayoutX(0);
        textArea2.setLayoutY(height - 30);
        textArea2.setPrefWidth(width);
        textArea2.setPrefHeight(30);
        */
        //root2.getChildren().add(textArea2);
        //root2.getChildren().add(imageView2);
        //Scene scene2 = new Scene(root2, width, height);
        
        
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}