package test1;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in test1.");
        
        GUI.launch(GUI.class, args);
        
        System.exit(0);
    }
}