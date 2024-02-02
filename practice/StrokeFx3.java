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

public class StrokeFx3 extends Application {
    @Override
    public void start(Stage stage) {
        //Font font = Font.loadFont("file:DungGeunMo.ttf", 50);
        Font font = Font.loadFont("file:DungGeunMo.ttf", 30);
        
        Text text = new Text();
        text.setFont(font);
        text.setX(50);
        text.setY(130);
        text.setFill(Color.RED);
        text.setText("게시판에 오신 걸 환영합니다.");
        
        Text text2 = new Text();
        text2.setFont(font);
        text2.setX(50);
        text2.setY(230);
        text2.setFill(Color.BLUE);
        text2.setText("글쓰기");
        
        Group root = new Group(text, text2);
        
        Scene scene = new Scene(root, 600, 800);
        
        stage.setTitle("StrokeFx3");
        
        stage.setScene(scene);
        
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
