package practice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class StrokeFx3 extends Application {
    @Override
    public void start(Stage stage) {
        //Font font = Font.loadFont("file:DungGeunMo.ttf", 50);
        Font font = Font.loadFont("file:DungGeunMo.ttf", 30);
        
        Text text1 = new Text();
        text1.setFont(font);
        text1.setX(50);
        text1.setY(130);
        text1.setFill(Color.RED);
        text1.setText("게시판에 오신 걸 환영합니다.");
        
        Text text2 = new Text();
        text2.setFont(font);
        text2.setX(50);
        text2.setY(230);
        text2.setFill(Color.BLUE);
        text2.setText("글쓰기");
        
        Text text3 = new Text();
        text3.setFont(font);
        text3.setX(50);
        text3.setY(330);
        text3.setFill(Color.WHITE);
        text3.setText("글내용");
        
        //Group root = new Group(text, text2);
        Group root = new Group();
        
        //System.out.println(root);
        //System.out.println(root.getChildren());
        //System.out.println(root.getChildren().add(text3));
        
        Line line = new Line();
        
        line.setStartX(100.);
        line.setStartY(150.);
        line.setEndX(500.);
        line.setEndY(150.);
        //line.setFill(Color.YELLOW);
        //line.setFill(Color.RED);
        
        
        Rectangle rectangle = new Rectangle();  

        rectangle.setX(150.0f); 
        rectangle.setY(75.0f); 
        rectangle.setWidth(300.0f); 
        rectangle.setHeight(150.0f);          
        rectangle.setFill(Color.GREY);
        
        
        root.getChildren().add(rectangle);
        
        root.getChildren().add(text1);
        root.getChildren().add(text2);
        root.getChildren().add(text3);
        
        root.getChildren().add(line);
        
        
        Scene scene = new Scene(root, 600, 800);
        
        scene.setFill(Color.BLACK);
        
        stage.setTitle("StrokeFx3");
        
        stage.setScene(scene);
        
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
