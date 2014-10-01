package assone;

/**
 * Created by jm on 9/24/2014.
 */
public class ThreadAfive implements Runnable {
    Thread th5;
    public ThreadAfive() {
        th5 = new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("Now we wait until second and third thread will finish");
        System.out.println("neco se stalo");
    }
}
