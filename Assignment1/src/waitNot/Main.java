package waitNot;

/**
 * Created by jm on 10/7/2014.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(new ThreadNew());
        th.start();

        th.wait(5000);
        th.notify();

        System.out.println("test");


    }
}
