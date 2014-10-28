package real;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Thread6 implements Runnable {
    Semaphore[] sem;
    String str;
    Thread thr;

    public Thread6(Semaphore[] semact, String s) {
        thr = new Thread();
        this.str = s;
        this.sem = semact;
        thr.start();
    }

    public void testMethod() {
        System.out.println("Thread #6 is running: " + thr.getName() + " / " + Thread.activeCount());
    }

    @Override
    public void run() {
        try {
            sem[5].acquire();
            sem[6].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testMethod();
        /*Thread d = Thread.currentThread();
        d.interrupt();
        System.out.println(d.isInterrupted());
        */

        sem[8].release();
        //System.out.println(sem[8].tryAcquire());

    }
}
