package test1;

import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

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

//import test1.Print;
//import Print.*;
//import Print;



public class GUI extends Application {
    @Override
    //public void start(Stage stage) throws FileNotFoundException {
    public void start(Stage stage) throws FileNotFoundException, UnknownHostException {
        System.out.println("Here is start under GUI in test1.");
        
        //Print.class;
        //Print.class.testtt();
        
        //Print.testtt();
        //Print p = new Print();
        //p.ppp;
        //test1.Print p = new test1.Print();
        //test1.Print.testtt();
        
        //Print.ppp;
        
        InetAddress ip = InetAddress.getLocalHost();
        
        String ipv4 = ip.getHostAddress();
        
        
        //short rand = ThreadLocalRandom.current().nextShort();
        //int rand = ThreadLocalRandom.current().nextInt();
        //int rand = ThreadLocalRandom.current().nextInt(0, 65535 + 1);
        int portRand = ThreadLocalRandom.current().nextInt(0, 65535 + 1);
        
        //String portStr = "7777";
        //int port = 7777;
        
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
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
        
        
        Print.append("IPv4: ");
        Print.append(ipv4);
        Print.append("\nPort Number: ");
        //Print.append(port);
        Print.append(portRand);
        
        Text text = new Text();
        //text.setText("IPv4: ".concat(ipv4));
        text.setText(Print.getAndReset());
        text.setFont(font);
        //text.setFill(Color.rgb(255, 255, 255));
        text.setFill(Color.rgb(0, 255, 0));
        text.setX(0);
        text.setY(32);
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("server");
        stage.setScene(scene);
        stage.show();
    }
}