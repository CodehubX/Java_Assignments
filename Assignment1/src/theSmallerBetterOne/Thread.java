package theSmallerBetterOne;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by jm on 10/14/2014.
 */
public class Thread implements Runnable {
    List<Semaphore> aq;
    List<Semaphore> re;
    String name;

    public Thread(List<Semaphore> ac, List<Semaphore> re, String name) {
        this.name = name;
        this.aq = ac;
        this.re = re;
    }

    public void testMethod() {
        System.out.println("Thread # is running: ");
    }

    @Override
    public void run() {
        for (int i = 0; i < aq.size(); i++) {
            aq.clear();
        }

        testMethod();

        for (int i = 0; i < re.size(); i++) {
            re.add(i);
        }
    }
}
