package practice;

import java.io.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.paint.*;
//import javafx.scene.shape.Box;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.Rotate;
import javafx.scene.image.*;

//import javafx.animation.RotateTransition;

public class EventHandlersFx3 extends Application {
    @Override
    //public void start(Stage stage) {
    public void start(Stage stage) throws FileNotFoundException {
        /*
        Box box = new Box();
        
        box.setWidth(150.);
        box.setHeight(150.);
        box.setDepth(150.);
        
        box.setTranslateX(350);
        box.setTranslateY(150);
        box.setTranslateZ(50);
        
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GREY);
        
        box.setMaterial(material);
        
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(100));
        rotateTransition.setNode(box);
        rotateTransition.setAxis(Rotate.X_AXIS);
        //rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(50);
        rotateTransition.setAutoReverse(false);
        */
        Text text = new Text("치거나 클릭하거나.");
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 15);
        text.setFont(font);
        
        //text.setFill(Color.RED);
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
        //rectangle.setWidth(300);
        //rectangle.setWidth(600);
        rectangle.setWidth(width);
        rectangle.setHeight(30);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.5));
        
        TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(100);
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                //rotateTransition.play();
                System.out.println(e.getCharacter());
            }
        };
        textField.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
        
        EventHandler<MouseEvent> eventHandlerBox = new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                //rotateTransition.stop();
                
                MouseButton mouseButton = e.getButton();
                System.out.println(mouseButton);
                
                switch(mouseButton) {
                    case MouseButton.PRIMARY: {
                        //material.setDiffuseColor(Color.RED);
                        rectangle.setFill(Color.rgb(255, 0, 0, 0.5));
                        break;
                    }
                    case MouseButton.SECONDARY: {
                        //material.setDiffuseColor(Color.BLUE);
                        rectangle.setFill(Color.rgb(0, 0, 255, 0.5));
                        break;
                    }
                    case MouseButton.MIDDLE: {
                        //material.setDiffuseColor(Color.WHITE);
                        rectangle.setFill(Color.rgb(0, 255, 0, 0.5));
                        break;
                    }
                    default: {
                    }
                };
                System.out.println(textField.getText());
            }
        };
        //box.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerBox);
        rectangle.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerBox);
        
        //Group root = new Group(box, textField, text);
        Group root = new Group();
        //root.getChildren().add(box);
        root.getChildren().add(imageView);
        root.getChildren().add(textField);
        root.getChildren().add(text);
        root.getChildren().add(rectangle);
        
        
        //Scene scene = new Scene(root, 600, 300);
        Scene scene = new Scene(root, width, height);
        
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        scene.setCamera(camera);
        
        stage.setResizable(false);
        stage.setTitle("EventHandlersFx3");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}