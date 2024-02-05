package test3;

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
    //static final String FILE_NAME = "system.ini";
    static final String FILE_NAME_CHAT = "chat.txt";
    //static final String FILE_TEXT = "I know I am clumsy.\nSo I should be more careful to live in safer way.";
}
class Mut {
    static String chat = new String();
    //synchronized static String chat = new String();
}
class ChatClient {
	static void client() {
		Socket socket = null;		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			//Sender sender = new Sender(socket);
			Sender2 sender = new Sender2(socket);
			Receiver receiver= new Receiver(socket);
			
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
            /*
            while(Fn.readText().length() > 0) {
                
                
                out = new DataOutputStream(socket.getOutputStream());
                name = "[".concat(String.valueOf(socket.getInetAddress())).concat(String.valueOf(socket.getPort())).concat("]");
                
                Thread.sleep(100);
            };
            */
            //System.out.println("Here is Sender2.");
            
            out = new DataOutputStream(socket.getOutputStream());
            name = "[".concat(String.valueOf(socket.getInetAddress())).concat(String.valueOf(socket.getPort())).concat("]");
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in, "Cp949");
        while(out != null) {
        //while(out != null && Fn.readText().length() > 0) {
            try {
                //out.writeUTF(name + scanner.nextLine());
                String chat = Fn.readText();
                //System.out.println(chat);
                
                //System.out.println("Here is Sender2.run()");
                
                if (chat.length() == 0) {
                    continue;
                } else {
                    out.writeUTF(name + chat);
                    Fn.writeText("");
                };
                
                //out.writeUTF(name + Fn.readText());
                //out.writeUTF(name + Mut.chat);
                //Thread.sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
    }
}
class Fn {
    //static void writeText() {
    static void writeText(String chat) {
        try (BufferedWriter bufferedWriter 
            //= new BufferedWriter(new FileWriter(Const.FILE_NAME))) 
            = new BufferedWriter(new FileWriter(Const.FILE_NAME_CHAT))) 
        {
            //bufferedWriter.write(Const.FILE_TEXT);
            bufferedWriter.write(chat);
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
    //static void readTextWithUpperCase() {
    //static void readText() {
    static String readText() {
        //String chat = new String();
        String chat = "";
        //StringBuilder chat = new StringBuilder();
        try (BufferedReader bufferedReader
            //= new BufferedReader(new FileReader(Const.FILE_NAME)))
            = new BufferedReader(new FileReader(Const.FILE_NAME_CHAT)))
        {
            while (bufferedReader.ready()) {
                //Print.justAppend(bufferedReader.readLine());
                //Print.justAppend("\n");
                //System.out.println(bufferedReader.readLine());
                String temp = bufferedReader.readLine();
                //System.out.println(temp);
                //chat.concat(bufferedReader.readLine());
                chat = chat.concat(temp);
                //chat.append(temp);
                //chat.append(bufferedReader.readLine());
                //System.out.println(chat);
            };
            //Print.resetAndPrint(Print.getPrint().toString().toUpperCase());
        } catch(IOException e) {
            e.printStackTrace();
        };
        //System.out.println(chat);
        //return chat;
        return chat.toString();
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
        rectangle.setWidth(width);
        rectangle.setHeight(30);
        rectangle.setFill(Color.rgb(0, 0, 0, 0.5));
        
        TextField textField = new TextField();
        textField.setLayoutX(50);
        textField.setLayoutY(100);
        
        EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                
                
                
                //System.out.println(e.getCode());
                //System.out.println(e.getText());
                //System.out.println(e.getCharacter());
                
                if (e.getCode() == KeyCode.ENTER) {
                    //System.out.println("enter");
                    Mut.chat = textField.getText();
                    //System.out.println(textField.getText());
                    System.out.println(Mut.chat);
                    Fn.writeText(Mut.chat);
                    //String temp = textField.getText();
                    //chat = textField.getText();
                    textField.clear();
                };
                
            }
        };
        //textField.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField);
        //textField.addEventHandler(KeyEvent.ANY, eventHandlerTextField);
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
                //System.out.println(textField.getText());
                //textField.clear();
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
        stage.setTitle("Test3");
        stage.setScene(scene);
        stage.show();
    }
}