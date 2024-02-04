package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

public class WritingPixelsFX2 extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("test.png"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        WritableImage wImage = new WritableImage(width, height);
        
        PixelReader pixelReader = image.getPixelReader();
        
        PixelWriter writer = wImage.getPixelWriter();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                
                writer.setColor(x, y, color);
            };
        };
        ImageView imageView = new ImageView(wImage);
        
        
        Rectangle rectangle = new Rectangle();
        
        //rectangle.setY(10);
        rectangle.setY(7);
        //rectangle.setWidth(300);
        rectangle.setWidth(width);
        rectangle.setHeight(30);
        rectangle.setFill(Color.BLUE);
        
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 30);
        
        Text text1 = new Text();
        text1.setFont(font);
        text1.setY(30);
        text1.setFill(Color.RED);
        text1.setText("게시판에 오신 걸 환영합니다.");
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(rectangle);
        root.getChildren().add(text1);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setTitle("WritingPixelsFX2");
        stage.setResizable(false);
        
        stage.setScene(scene);
        
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}