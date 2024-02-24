package main4;

import java.io.*;

import javafx.scene.shape.Rectangle;

public class Fn {
    
    
    
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
//                
                chat = chat.concat(temp);
                chat = chat.concat("\n");
            };
        } catch(IOException e) {
            e.printStackTrace();
        };
        return chat;
    }
    
    
}