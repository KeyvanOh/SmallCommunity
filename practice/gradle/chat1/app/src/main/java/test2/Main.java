package test2;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main in test2.");
        
        //Server.serverMain();
        Server server = new Server();
        server.start();
        
        //Server.serverMain();
        
        GUI.launch(GUI.class, args);
        
        System.exit(0);
    }
}