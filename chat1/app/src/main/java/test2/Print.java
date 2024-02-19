package test2;

public class Print {
    private static StringBuilder print = new StringBuilder();
    private static void reset() {
        print.setLength(0);
    }
    public static <T> void append(T t) {
        print.append(t);
    }
    public static String getAndReset() {
        String str = print.toString();
        reset();
        return str;
    }
    public static <T> void print(T t) {
        append(t);
        System.out.print(print);
        reset();
    }
}