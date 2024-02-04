package practice;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

public class EventHandlersFx2 extends Application {
    @Override
    public void start(Stage stage) {
        Box box = new Box();
        
        box.setWidth(150.);
        box.setHeight(150.);
        box.setDepth(100.);
        
        box.setTranslateX(350);
        box.setTranslateY(150);
        box.setTranslateZ(50);
        
        Text text = new Text("Type whatever to rotate, click the box to stop.");
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 15);
        text.setFont(font);
        
        text.setFill(Color.RED);
        
        text.setX(20);
        text.setY(50);
        
        PhongMaterial material = new PhongMaterial();
        //material.setDiffuseColor(Color.BLUE);
        material.setDiffuseColor(Color.GREY);
        
        box.setMaterial(material);
        
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        rotateTransition.setNode(box);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(50);
        rotateTransition.setAutoReverse(false);
        
        TextField textField = new TextField();
        
        textField.setLayoutX(50);
        textField.setLayoutY(100);
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                rotateTransition.play();
                //System.out.println(e);
                //System.out.println(e.button);
                //System.out.println(e.getButton());
                //System.out.println(e.getCode());
                System.out.println(e.getCharacter());
                //System.out.println(e.getText());
                //System.out.println(textField);
                //System.out.println(textField.getText());
            }
        };
        textField.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
        
        //EventHandler<javafx.scene.input.MouseEvent> eventHandlerBox =
        //    new EventHandler<javafx.scene.input.MouseEvent>()
        EventHandler<MouseEvent> eventHandlerBox = new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                rotateTransition.stop();
                //System.out.println(e);
                
                
                
                //System.out.println(e.getButton());
                
                MouseButton mouseButton = e.getButton();
                System.out.println(mouseButton);
                
                
                
                switch(mouseButton) {
                    case MouseButton.PRIMARY: {
                        material.setDiffuseColor(Color.RED);
                        break;
                    }
                    case MouseButton.SECONDARY: {
                        material.setDiffuseColor(Color.BLUE);
                        break;
                    }
                    case MouseButton.MIDDLE: {
                        material.setDiffuseColor(Color.WHITE);
                        break;
                    }
                    default: {
                    }
                };
                
                
                
                
                
                System.out.println(textField.getText());
            }
        };
        box.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandlerBox);
        
        Group root = new Group(box, textField, text);
        
        Scene scene = new Scene(root, 600, 300);
        
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        scene.setCamera(camera);
        
        stage.setResizable(false);
        stage.setTitle("EventHandlersFx2");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
