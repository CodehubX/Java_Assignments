package real;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Thread7 implements Runnable {
    Semaphore[] sem;
    String str;
    Thread thr;

    public Thread7(Semaphore[] semact, String s) {
        thr = new Thread();
        this.str = s;
        this.sem = semact;
        thr.start();
    }

    public void testMethod() {
        System.out.println("Thread #7 is running - finito: " + thr.getName() + " / " + Thread.activeCount());
    }

    @Override
    public void run() {
        sem[7].release();
        sem[8].release();
        testMethod();

    }
}
