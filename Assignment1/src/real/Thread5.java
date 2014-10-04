package real;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Thread5  implements Runnable {
    Semaphore[] sem;
    String str;
    Thread thr;

    public Thread5(Semaphore[] semact, String s) {
        thr = new Thread();
        this.str = s;
        this.sem = semact;
        thr.start();
    }

    public void testMethod() {
        System.out.println("Thread #5 is running: " + thr.getName() + " / " + Thread.activeCount());
    }

    @Override
    public void run() {
        sem[3].release();
        sem[4].release();
        testMethod();

        try {
            sem[7].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
