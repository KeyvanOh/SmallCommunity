//
//code1
//
import java.net.*;
import java.io.*;
import java.util.*;
class Cosnt {
}
class Mut {
    static ServerSocket serverSocket = null;
    static Socket socket = null;
}
class Print {
    private static StringBuilder print = new StringBuilder();
    private static void reset() {
        print.setLength(0);
    }
    static void printAndReset() {
        System.out.print(print);
        reset();
    }
    static <T> void print(T t) {
        print.append(t);
        printAndReset();
    }
}
class Fn {
    static void runMain() {
        Mut.serverSocket = null;
        try {
            server();
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    private static void server() {
        try {
            Mut.serverSocket = new ServerSocket(7777);
            Print.print("서버가 작동되었습니다.\n");
//            
            while(true) {
                Mut.socket = Mut.serverSocket.accept();
//                
                Sender sender = new Sender(Mut.socket);
                Receiver receiver = new Receiver(Mut.socket);
//                
                sender.start();
                receiver.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        };
    }
}
/*
class ServerMain {
    public static void main(String[] args) {
        Fn.runMain();
    }
}
*/
class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    String name;
    public Sender(Socket socket) {
        super();
        this.socket = socket;
        try {
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
            try {
                out.writeUTF(name + scanner.nextLine());
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Sender err\n");
                break;
            };
        };
    }
}
class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch(Exception e) {
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        while(in != null) {
            try {
                System.out.println(in.readUTF());
            } catch(Exception e) {
                e.printStackTrace();
                Print.print("Receiver err\n");
                break;
            };
        };
    }
}