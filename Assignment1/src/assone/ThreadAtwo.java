package assone;

import java.util.concurrent.Semaphore;

/**
 * Created by jm on 9/24/2014.
 */
public class ThreadAtwo implements Runnable {
    Thread th2;
    Semaphore sem = new Semaphore(2) ;


    public ThreadAtwo() {
        th2 = new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("We are in the second thread: " + Thread.currentThread());
        try {
            th2.sleep(5000);
            sem.acquire();

            Thread five = new Thread(new ThreadAfive());
            five.start();
            System.out.println("test 2 zda neco");
        } catch (InterruptedException e) {
            e.printStackTrace();
            sem.release();
        }
    }


}
