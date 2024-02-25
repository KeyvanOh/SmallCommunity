package main1;

import java.io.*;
import java.net.*;

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

import com.fasterxml.jackson.core.*;

import com.fasterxml.jackson.databind.*;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException, UnknownHostException {
        System.out.println("Here is start under GUI in main1.");
        
        InetAddress ip = InetAddress.getLocalHost();
        String ipv4 = ip.getHostAddress();
        
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
        int port = 0;
        //Print.append(port);
        Print.append("hidden");
        
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
        
        EventHandler<MouseEvent> eventHandlerButtonShow = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String mouseEvent = String.valueOf(e.getEventType());
                switch(mouseEvent) {
                    case "MOUSE_ENTERED": {
                        buttonShow.setFill(Color.rgb(0, 0, 255));
                        break;
                    }
                    case "MOUSE_EXITED": {
                        buttonShow.setFill(Color.rgb(255, 0, 0));
                        break;
                    }
                    case "MOUSE_CLICKED": {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            try {
                                JsonFactory jsonFactory = new JsonFactory();
                                
                                JsonParser jsonParser = jsonFactory.createParser(new File("port.json"));
                                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                                    String fieldName = jsonParser.getCurrentName();
                                    if ("port".equals(fieldName)) {
                                        jsonParser.nextToken();
                                        System.out.println(jsonParser.getText());
                                        
                                        Print.append("IPv4: ");
                                        Print.append(ipv4);
                                        Print.append("\nPort Number: ");
                                        int port = Integer.parseInt(jsonParser.getText());
                                        Print.append(port);
                                        
                                        text.setText(Print.getAndReset());
                                    };
                                };
                            } catch(IOException err) {
                                err.printStackTrace();
                            };
                        };
                        break;
                    }
                    default: {
                    }
                };
            }
        };
        buttonShow.addEventHandler(MouseEvent.ANY, eventHandlerButtonShow);
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        root.getChildren().add(buttonShow);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }
}