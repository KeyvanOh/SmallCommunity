package main4;

public class Print {
    private static StringBuilder print = new StringBuilder();
    private static void reset() {
        print.setLength(0);
    }
    static void printAndReset() {
        System.out.print(print);
        reset();
    }
    static <T> void print(T t) {
        append(t);
        printAndReset();
    }
    static <T> void append(T t) {
        print.append(t);
    }
    static String getString() {
        return print.toString();
    }
}