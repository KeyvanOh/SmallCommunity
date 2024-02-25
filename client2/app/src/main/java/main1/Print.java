package main1;

public class Print {
    private static StringBuilder print = new StringBuilder();
    private static void reset() {
        print.setLength(0);
    }
    public static void printAndReset() {
        System.out.print(print);
        reset();
    }
    public static <T> void print(T t) {
        append(t);
        printAndReset();
    }
    public static <T> void append(T t) {
        print.append(t);
    }
    public static String getString() {
        return print.toString();
    }
    public static String getAndReset() {
        //String str = print.toString();
        String str = getString();
        reset();
        return str;
    }
}