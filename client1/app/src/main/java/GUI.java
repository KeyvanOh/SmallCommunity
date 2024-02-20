import java.io.*;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.*;
//import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;


import javafx.beans.value.*;
//import javafx.scene.layout.Region;
import javafx.scene.layout.*;


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
        System.out.println("Here is start under GUI.");
        
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
        
        
        
        TextField textField = new TextField();
        textField.setLayoutX(0);
        textField.setLayoutY(height - 30);
        //textField.setLayoutY(-30);
        textField.setPrefWidth(width);
        
        /*
        TextField textField2 = new TextField();
        textField2.setLayoutX(0);
        textField2.setLayoutY(height - 60);
        textField2.setPrefWidth(width);
        */
        
        
        /*
        TextField textField3 = new TextField();
        textField3.setLayoutX(0);
        //textField3.setLayoutY(height - 90);
        //textField3.setLayoutY(0);
        textField3.setLayoutY(30);
        textField3.setPrefWidth(width);
        textField3.setPrefHeight(100);
        //textField3.setPrefWidth(width);
        */
        
        
        TextArea textArea = new TextArea();
        textArea.setLayoutX(0);
        //textArea.setLayoutY(30);
        textArea.setLayoutY(height - 30);
        //textArea.setLayoutY(-30);
        textArea.setPrefWidth(width);
        textArea.setPrefHeight(30);
        
        
        
        
        
        
        //textArea.setFill(Color.rgb(0, 0, 0));
        //textArea.getSkin();
        //System.out.println(textArea.getSkin());
        
        
        
        /*
        TextArea textAreaaaa = new TextArea("I have an ugly white background :-(");
        // we don't use lambdas to create the change listener since we use
        // the instance twice via 'this' (see *)
        textAreaaaa.skinProperty().addListener(new ChangeListener<Skin<?>>() {

            @Override
            public void changed(
              ObservableValue<? extends Skin<?>> ov, Skin<?> t, Skin<?> t1) {
                if (t1 != null && t1.getNode() instanceof Region) {
                    Region r = (Region) t1.getNode();
                    r.setBackground(Background.EMPTY);

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Region).
                            map(n -> (Region) n).
                            forEach(n -> n.setBackground(Background.EMPTY));

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Control).
                            map(n -> (Control) n).
                            forEach(c -> c.skinProperty().addListener(this)); // *
                }
            }
        });        
        */
        
        //textField.setStyle("");
        //textField.setStyle(
        //    "-fx-background-color: transparent;"
        //);
        
        /*
        textField.setStyle(
            "-fx-background-color: transparent;"
            + "-fx-color: transparent;"
        );
        */
        
        //textField.setStyle("-fx-color: transparent;");
        
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                //System.out.println(e); 
                //System.out.println(e.getCode()); 
                //System.out.println(e.getText()); 
                //System.out.println(e.getCharacter()); 
                
                
                
                //System.out.println(textField.getText()); 
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
        //imageView.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        //textField.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        //textField.addEventHandler(KeyEvent.ANY, eventHandlerTextField);
        textArea.addEventHandler(KeyEvent.ANY, eventHandlerTextField);
        //root.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        
        
        
        
        
        
        
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        
        //root.getChildren().add(textField);
        //root.getChildren().add(textField2);
        
        //root.getChildren().add(textField3);
        root.getChildren().add(textArea);
        //root.getChildren().add(textAreaaaa);
        
        
        root.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        
        
        
        
        Scene scene = new Scene(root, width, height);
        
        //scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}