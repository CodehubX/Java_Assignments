package theSmallerBetterOne;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/14/2014.
 */
public class Main {
    public static void main(String[] args) {


        ExecutorService threadpool = Executors.newFixedThreadPool(8);
        List<Semaphore> aq = null;
        List<Semaphore> re = null;

        for (int i = 0; i < aq.size(); i++) {
            aq.add(i, new Semaphore(0));
            // initial number when Semaphore starts, how many permits does it have;
            // wenn >= 0 dann laufen alle
        }
        threadpool.submit(new Thread(aq, re, "#1"));
        threadpool.submit(new Thread(aq, re, "#2"));
        threadpool.submit(new Thread(aq, re, "#3"));
        threadpool.submit(new Thread(aq, re, "#4"));
        threadpool.submit(new Thread(aq, re, "#5"));
        threadpool.submit(new Thread(aq, re, "#6"));
        threadpool.submit(new Thread(aq, re, "#7"));

        threadpool.shutdown();
    }
}
