package main1;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in main1.");
        
        Server server = new Server();
        server.start();
        
        GUI.launch(GUI.class, args);
        
        System.exit(0);
    }
}