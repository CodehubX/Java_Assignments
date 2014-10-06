package assone;

/**
 * Created by jm on 9/24/2014.
 */
public class ThreadAfour implements Runnable {
    Thread th4;

    public ThreadAfour() {
        th4 = new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("We are in the fourth thread" + Thread.currentThread());

    }
}
