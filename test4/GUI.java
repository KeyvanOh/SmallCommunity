package test4;

import java.io.*;
import java.util.*;
import java.net.*;

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

class Const {
    static final String FILE_NAME_CHAT = "chat.txt";
}
class Mut {
}
class ChatClient {
    static void client() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7777);
            System.out.println("서버에 연결되었습니다.");
            
            Sender2 sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);
            
            sender.start();
            receiver.start();
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
}
class Sender2 extends Thread {
    Socket socket;
    DataOutputStream out;
    String name;
    public Sender2(Socket socket) {
        super();
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            name = "[".concat(String.valueOf(socket.getInetAddress())).concat(String.valueOf(socket.getPort())).concat("]");
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(out != null) {
            try {
                String chat = Fn.readText();
                if(chat.length() == 0) {
                    continue;
                } else {
                    out.writeUTF(name.concat(chat));
                    Fn.writeText("");
                };
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
    }
}
class Fn {
    static void writeText(String chat) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Const.FILE_NAME_CHAT))) {
            bufferedWriter.write(chat);
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
    static String readText() {
        String chat = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Connst.FILE_NAME_CHAT))) {
            while(bufferedReader.ready()) {
                String temp = bufferedReader.readLine();
                chat = chat.concat(temp);
            };
        } catch(IOException e) {
            e.printStackTrace();
        };
        return chat;
    }
}
public class GUI extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Socket socket;
        DataOutputStream out;
        String name;
        
        Font font = Font.loadFont("file.DunGeunMo.ttf", 15);
        
        Text text = new Text("Typing or Clicking");
        text.setFont(font);
        text.setFill(Color.rgb(255, 0, 0, 1));
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
        
        Eventhandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            
        };
        
        
        
        
    }
    
}