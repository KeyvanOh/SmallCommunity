package test2;

import java.io.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.Rotate;
import javafx.scene.image.*;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Font font = Font.loadFont("file.DunGeunMo.ttf", 15);
        
        Text text = new Text("타이핑하거나 클릭하거나");
        text.setFont(font);
        text.setFill(Color.rgb(255, 0, 0, 1.));
        text.setX(20);
        text.setY(50);
        
        Image image = new Image(new FileInputStream("test.png"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter writer = wImage.getPixelWriter();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                
                writer.setColor(x, y, color);
            };
        };
        ImageView imageView = new ImageView(wImage);
        
        Rectangle rectangle = new Rectangle();
        rectangle.setY(7);
        rectangle.setWidth(width);
        rectangle.setHeight(30);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.5));
        
        TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(100);
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println(e.getCharacter());
            }
        };
        textField.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
        
        EventHandler<MouseEvent> eventHandlerRectangle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //System.out.println(e);
                MouseButton mouseButton = e.getButton();
                System.out.println(mouseButton);
                
                switch(mouseButton) {
                    case MouseButton.PRIMARY: {
                        rectangle.setFill(Color.rgb(255, 0, 0, 0.5));
                        break;
                    }
                    case MouseButton.SECONDARY: {
                        rectangle.setFill(Color.rgb(0, 0, 255, 0.5));
                        break;
                    }
                    case MouseButton.MIDDLE: {
                        rectangle.setFill(Color.rgb(0, 255, 0, 0.5));
                        break;
                    }
                    default: {
                    }
                };
                System.out.println(textField.getText());
                textField.clear();
            }
        };
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerRectangle);
        
        //EventHandler<ScrollEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
        EventHandler<ScrollEvent> eventHandlerRectangle2 = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                //System.out.println(e.getCharacter());
                //System.out.println(e);
                System.out.println(e.getDeltaY());
                //System.out.println(e.getTextDeltaY());
                //System.out.println(e.getTotalDeltaY());
                //System.out.println(e.getTextDeltaYUnits());
                //System.out.println(e.getEventType());
                //System.out.println(e.getMultiplierY());
            }
        };
        //rectangle.addEventHandler(ScrollEvent.MOUSE_CLICKED, eventHandlerRectangle2);
        rectangle.addEventHandler(ScrollEvent.SCROLL, eventHandlerRectangle2);
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textField);
        root.getChildren().add(text);
        root.getChildren().add(rectangle);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Test2");
        stage.setScene(scene);
        stage.show();
    }
}