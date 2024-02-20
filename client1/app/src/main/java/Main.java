import javafx.application.Application;

class Main {
    public static void main(String[] args) {
        System.out.println("Here is Main.");
        
        //Server server = new Server();
        //server.start();
        
        GUI.launch(GUI.class, args);
        
        System.exit(0);
    }
}