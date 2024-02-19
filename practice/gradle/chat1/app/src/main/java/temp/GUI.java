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

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Here is start under GUI.");
        
        //Inet4Address inet4Address = new Inet4Address();
        //System.out.println(Inet4Address.getAddress());
        
        //InetAddress inet = InetAddress.getByName(ip);
        
        InetAddress ip = InetAddress.getLocalHost();
        //System.out.println(IP);
        //System.out.println(ip.getHostAddress());
        
        String ipv4 = ip.getHostAddress();
        
        
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);

        //int width = 256;
        //int height = 128;
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
        
        Text text = new Text();
        //text.setText("한글");
        //text.setText("한글" + iPv4);
        text.setText("IPv4: " + ipv4);
        text.setFont(font);
        //text.setFill(Mut.color);
        text.setFill(Color.rgb(255, 255, 255));
        text.setX(0);
        //text.setY(Mut.textY);
        text.setY(32);
        
        
        
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        
        
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        //stage.setTitle("server?");
        stage.setTitle("server");
        stage.setScene(scene);
        stage.show();
    }
}