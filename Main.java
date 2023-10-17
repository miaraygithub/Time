// the "main" class for the program
public class Main {
    public static void main(String args[]) {
        Time t1 = new Time();              // midnight
        Time t2 = new Time(17, 30, 0);     // 5:30 PM
        Time t3 = Time.fromString("14:15:56");

        // add an hour onto t1, then print it in both formats
        t1.increment(3600);
        t1.print(true);
        t1.print(false);

        // add an hour and a half, and 30 seconds onto t2
        t2.increment(5430);

        // print t2 in both formats
        t2.print(true);
        t2.print(false);

        // add 5 seconds to t3, then prints it in both formats
        t3.increment(5);
        t3.print(true);
        t3.print(false);
    }
}