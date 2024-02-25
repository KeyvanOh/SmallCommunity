package main1;

import java.net.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Mut {
    public static int port = ThreadLocalRandom.current().nextInt(0, 65535 + 1);
    public static ServerSocket serverSocket = null;
    public static Socket socket = null;
    
    public static boolean request = true;
    
    
    public static String userIp = new String();
    public static String date = new String();
    public static String idTime = new String();
    public static String posted = new String();
    public static String postNumberTemp = new String();
    
    
    public static int postNumber = 0;
}