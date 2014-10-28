package real;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Thread1 implements Runnable {
    Semaphore[] sem;
    String str;
    Thread thr;

    public Thread1(Semaphore[] semact, String name) {
        thr = new Thread();
        this.str = name;
        this.sem = semact;
        thr.start();
    }

    public void testMethod() {
        System.out.println("Thread #1 is running: " + thr.getName() + " / " + Thread.activeCount());
    }

    @Override
    public void run() {
        testMethod();
        sem[0].release();
        sem[1].release();
        sem[2].release();
    }
}
