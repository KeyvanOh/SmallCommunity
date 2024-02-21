package main2;

import java.io.*;

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
            }
        };
        textArea.addEventHandler(KeyEvent.KEY_RELEASED, eventHandlerTextArea);
        
        Rectangle rectangleYourIP = new Rectangle();
        rectangleYourIP.setY(7);
        rectangleYourIP.setWidth(width);
        rectangleYourIP.setHeight(30);
        rectangleYourIP.setFill(Color.rgb(255, 0, 0));
        rectangleYourIP.setOpacity(0.5);
        
        Rectangle rectangleServerIP = new Rectangle();
        rectangleServerIP.setY(37);
        rectangleServerIP.setWidth(width);
        rectangleServerIP.setHeight(30);
        rectangleServerIP.setFill(Color.rgb(0, 0, 255));
        rectangleServerIP.setOpacity(0.5);
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        Text textYourIP = new Text();
        textYourIP.setText("Your IP: ");
        textYourIP.setFont(font);
        textYourIP.setFill(Color.rgb(255, 255, 255));
        textYourIP.setX(0);
        textYourIP.setY(30);
        
        Text textServerIP = new Text();
        textServerIP.setText("Server IP: ");
        textServerIP.setFont(font);
        //textServerIP.setFill(Color.rgb(0, 0, 0));
        textServerIP.setFill(Color.rgb(255, 255, 255));
        textServerIP.setX(0);
        //textServerIP.setY(30);
        textServerIP.setY(60);
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textArea);
        root.getChildren().add(rectangleYourIP);
        root.getChildren().add(rectangleServerIP);
        root.getChildren().add(textYourIP);
        root.getChildren().add(textServerIP);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}