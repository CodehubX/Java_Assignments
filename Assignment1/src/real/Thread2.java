package real;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Thread2 implements Runnable {
    Semaphore[] sem;
    String str;
    Thread thr;

    public Thread2(Semaphore[] semact, String s) {
        thr = new Thread();
        this.str = s;
        this.sem = semact;
        thr.start();
    }

    public void testMethod() {
        System.out.println("Thread #2 is running: " + thr.getName() + " / " + Thread.activeCount());
    }

    @Override
    public void run() {
        try {
            sem[0].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testMethod();

        //System.out.println(sem[3].tryAcquire());
        //System.out.println(sem[3].availablePermits());
        sem[3].release();

    }
}
