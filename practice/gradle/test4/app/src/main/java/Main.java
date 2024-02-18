
import com.fasterxml.jackson.core.*;
import javafx.application.Application;

class Main {
    
    public static void main(String[] args) {
        
        
        System.out.println("Here is Main.");
        
        ChatClient.client();
        GUI.launch(GUI.class, args);
        System.exit(0);        
    }


}