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
class Print {
    private static StringBuilder print = new StringBuilder();
    private static void reset() {
        print.setLength(0);
    }
    static void printAndReset() {
        System.out.print(print);
        reset();
    }
    static <T> void print(T t) {
        print.append(t);
        printAndReset();
    }
}
class ChatClient {
    static void client() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7777);
            System.out.println("서버에 연결되었습니다.");
            
            Sender2 sender = new Sender2(socket);
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
class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(in != null) {
            try {
                System.out.println(in.readUTF());
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Const.FILE_NAME_CHAT))) {
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
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    //Mut.chat = textField.getText();
                    String chat = textField.getText();
                    System.out.println(chat);
                    Fn.writeText(chat);
                    textField.clear();
                };
            }
        };
        textField.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        
        EventHandler<MouseEvent> eventHandlerRectangle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
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
            }
        };
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerRectangle);
        
        EventHandler<ScrollEvent> eventHandlerRectangle2 = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                System.out.println(e.getDeltaY());
            }
        };
        rectangle.addEventHandler(ScrollEvent.SCROLL, eventHandlerRectangle2);
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(textField);
        root.getChildren().add(text);
        root.getChildren().add(rectangle);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Test4");
        stage.setScene(scene);
        stage.show();
    }
}
