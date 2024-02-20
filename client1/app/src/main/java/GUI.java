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
        textField.setPrefWidth(width);
        
        TextField textField2 = new TextField();
        textField2.setLayoutX(0);
        textField2.setLayoutY(height - 60);
        textField2.setPrefWidth(width);
        
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
        textArea.setLayoutY(30);
        textArea.setPrefWidth(width);
        
        
        
        
        
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textField);
        root.getChildren().add(textField2);
        //root.getChildren().add(textField3);
        root.getChildren().add(textArea);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Client");
        stage.setScene(scene);
        
        stage.show();
    }
}