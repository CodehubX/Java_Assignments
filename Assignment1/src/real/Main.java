package real;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/4/2014.
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService threadpool = Executors.newFixedThreadPool(8);
        Semaphore[] sem = new Semaphore[10];

        for (int i = 0; i < sem.length; i++) {
            sem[i] = new Semaphore(5);
        }
        threadpool.submit(new Thread1(sem,"#1"));
        threadpool.submit(new Thread2(sem,"#2"));
        threadpool.submit(new Thread3(sem,"#3"));
        threadpool.submit(new Thread4(sem,"#4"));
        threadpool.submit(new Thread5(sem,"#5"));
        threadpool.submit(new Thread6(sem,"#6"));
        threadpool.submit(new Thread7(sem,"#7"));

        threadpool.shutdown();

    }


}
