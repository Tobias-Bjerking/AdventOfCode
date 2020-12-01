package shared;

import java.time.LocalDateTime;

public class Utils {
    static int time;


    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(int s) {
        System.out.println(s);
    }

    public static void start() {
        time = LocalDateTime.now().getNano();
    }

    public static void stop() {
        print("Time: " + String.valueOf((LocalDateTime.now().getNano() - time) / 1000) + " µs\n");
    }
}
