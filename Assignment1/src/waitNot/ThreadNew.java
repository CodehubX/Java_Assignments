package waitNot;

/**
 * Created by jm on 10/7/2014.
 */
public class ThreadNew implements Runnable {

    public ThreadNew() {

    }

    @Override public void run() {
        for (int i = 0 ; i <1000; i=i+5) {
            System.out.println(i);
        }
    }
}
