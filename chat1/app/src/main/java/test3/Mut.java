package test3;

import java.net.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mut {
    public static int port = ThreadLocalRandom.current().nextInt(0, 65535 + 1);
    public static ServerSocket serverSocket = null;
    public static Socket socket = null;
    
    
    
    public static boolean request = true;
}