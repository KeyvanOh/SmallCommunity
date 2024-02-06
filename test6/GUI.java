//javac --module-path javafx-sdk-21.0.1\lib --add-modules javafx.controls,javafx.fxml test6\Main.java
package test6;

import java.io.*;
import java.net.*;

//import javafx.application.Application;
//import javafx.application.Platform;
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

class Const {
    static final String FILE_NAME_CHAT = "chat.txt";
    static final String FILE_NAME_RECEIVE = "receive.txt";
}
class Mut {
    static String chatStack = new String();
    static double textY = 60.;
    static Color color = Color.rgb(255, 255, 255);
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
        //print.append(t);
        append(t);
        printAndReset();
    }
    static <T> void append(T t) {
        print.append(t);
    }
    static String getString() {
        return print.toString();
    }
}
class ChatClient {
    static void client() {
        Socket socket = null;
        try {
            Fn.writeText("", Const.FILE_NAME_CHAT);
            Fn.writeText("", Const.FILE_NAME_RECEIVE);
            
            socket = new Socket("localhost", 7777);
            Print.print("서버에 연결되었습니다.\n");
            
            Sender2 sender = new Sender2(socket);
            Receiver2 receiver = new Receiver2(socket);
            
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
            
            //name = "[".concat(String.valueOf(socket.getInetAddress())).concat(String.valueOf(socket.getPort())).concat("]");
            
            Print.append("[");
            Print.append(socket.getInetAddress());
            Print.append(socket.getPort());
            Print.append("]");
            
            name = Print.getString();
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(out != null) {
            try {
                String chat = Fn.readText(Const.FILE_NAME_CHAT);
                if(chat.length() == 0) {
                    continue;
                } else {
                    String chatScreen = Fn.readText(Const.FILE_NAME_RECEIVE);
                    chatScreen = chatScreen.concat(chat);
                    Fn.writeText(chatScreen, Const.FILE_NAME_RECEIVE);
                    
                    
                    Print.append(chat);
                    
                    out.writeUTF(name.concat(chat));
                    //out.writeUTF(Print.getString());
                    
                    
                    Fn.writeText("", Const.FILE_NAME_CHAT);
                };
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
    }
}
class Receiver2 extends Thread {
    Socket socket;
    DataInputStream in;
    public Receiver2(Socket socket) {
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
            try{
                String receive = in.readUTF();
                
                Mut.chatStack = Fn.readText(Const.FILE_NAME_RECEIVE);
                Mut.chatStack = Mut.chatStack.concat(receive).concat("\n");
                
                Fn.writeText(Mut.chatStack, Const.FILE_NAME_RECEIVE);
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}
class Fn {
    static void writeText(String chat, String path) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(chat);
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
    static String readText(String path) {
        String chat = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while(bufferedReader.ready()) {
                String temp = bufferedReader.readLine();
                
                chat = chat.concat(temp);
                chat = chat.concat("\n");
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
        
        Image image = new Image(new FileInputStream("bg.png"));
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        //Font font = Font.loadFont("file:DungGeunMo.ttf", 30);
        Font font = Font.loadFont("file:DungGeunMo.ttf", 16);
        
        Text text = new Text();
        text.setText("text initialized");
        text.setFont(font);
        //text.setFill(Color.rgb(255, 0, 0, 1.));
        //text.setFill(Color.rgb(255, 255, 255, 1.));
        text.setFill(Mut.color);
        text.setX(0);
        text.setY(Mut.textY);
        
        PixelReader pixelReader = image.getPixelReader();
        
        WritableImage wImage = new WritableImage(width, height);
        PixelWriter writer = wImage.getPixelWriter();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //Color color = pixelReader.getColor(x, y);
                Color c = pixelReader.getColor(x, y);
                
                //writer.setColor(x, y, color);
                writer.setColor(x, y, c);
            };
        };
        ImageView imageView = new ImageView(wImage);
        
        Rectangle rectangle = new Rectangle();
        rectangle.setY(7);
        rectangle.setWidth(width);
        rectangle.setHeight(30);
        //rectangle.setFill(Color.rgb(0, 0, 0, 0.5));
        rectangle.setFill(Mut.color);
        rectangle.setOpacity(0.5);
        
        TextField textField = new TextField();
        
        textField.setLayoutX(0);
        textField.setLayoutY(height - 30);
        //textField.setX(500);
        //textField.setSize(500, 100);
        //textField.setPrefWidth(500);
        textField.setPrefWidth(width);
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String tempRead = Fn.readText(Const.FILE_NAME_RECEIVE);
                text.setText(tempRead);
                
                KeyCode keyCode = e.getCode();
                
                //if(e.getCode() == KeyCode.ENTER) {
                if(keyCode == KeyCode.ENTER) {
                    String chat = textField.getText();
                    System.out.println(chat);
                    
                    Fn.writeText(chat, Const.FILE_NAME_CHAT);
                    
                    textField.clear();
                //} else if(keyCode == KeyCode.ESC) {
                } else if(keyCode == KeyCode.ESCAPE) {
                    Platform.exit();
                    System.exit(0);
                };
            }
        };
        textField.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerTextField);
        
        EventHandler<MouseEvent> eventHandlerRectangle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                MouseButton mouseButton = e.getButton();
                //System.out.println(mouseButton);
                //Color color = new Color();
                //Color color;
                //Color color = Color.rgb(255, 255, 255);
                //Mut.color = Color.rgb(255, 255, 255);
                switch(mouseButton) {
                    case MouseButton.PRIMARY: {
                        //color = Color.rgb(255, 255, 255);
                        //color = Color.rgb(255, 0, 0);
                        Mut.color = Color.rgb(255, 0, 0);
                        //color = color.setOpacity(0.5);
                        //color = color.setOpacity(0.5);
                        //rectangle.setFill(Color.rgb(255, 0, 0, 0.5));
                        //text.setFill(Color.rgb(255, 255, 255, 1.));
                        //rectangle.setFill(color);
                        //rectangle.setFill(color).setOpacity(0.5);
                        //rectangle.setOpacity(0.5);
                        //text.setFill(color);
                        break;
                    }
                    case MouseButton.SECONDARY: {
                        //color = Color.rgb(0, 0, 255);
                        Mut.color = Color.rgb(0, 0, 255);
                        //rectangle.setFill(Color.rgb(0, 0, 255, 0.5));
                        break;
                    }
                    case MouseButton.MIDDLE: {
                        //color = Color.rgb(0, 255, 0);
                        Mut.color = Color.rgb(0, 255, 0);
                        //rectangle.setFill(Color.rgb(0, 255, 0, 0.5));
                        break;
                    }
                    default: {
                    }
                };
                //text.setFill(color);
                //rectangle.setFill(color);
                text.setFill(Mut.color);
                rectangle.setFill(Mut.color);
                rectangle.setOpacity(0.5);
            }
        };
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerRectangle);
        
        EventHandler<ScrollEvent> eventHandlerRectangle2 = new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent e) {
                double wheel = e.getDeltaY();
                //System.out.println(wheel);
                
                Mut.textY += wheel;
                text.setY(Mut.textY);
            }
        };
        rectangle.addEventHandler(ScrollEvent.SCROLL, eventHandlerRectangle2);
        
        EventHandler<MouseEvent> eventHandlerImageView = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String tempRead = Fn.readText(Const.FILE_NAME_RECEIVE);
                text.setText(tempRead);
            }
        };
        imageView.addEventHandler(MouseEvent.MOUSE_MOVED, eventHandlerImageView);
        
        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(text);
        root.getChildren().add(textField);
        root.getChildren().add(rectangle);
        
        Scene scene = new Scene(root, width, height);
        
        stage.setResizable(false);
        stage.setTitle("Test6");
        stage.setScene(scene);
        stage.show();
    }
}