//
//code2
//
//javac --module-path javafx-sdk-21.0.1\lib --add-modules javafx.controls,javafx.fxml test6\Main.java
package test6;
import javafx.application.Application;
public class Main {
    public static void main(String[] args) {
        ChatClient.client();
        GUI.launch(GUI.class, args);
        System.exit(0);
    }
}
