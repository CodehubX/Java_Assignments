package assone;

/**
 * Created by jm on 9/24/2014.
 */
public class ThreadAone implements Runnable {

    Thread th1;
    public ThreadAone() {
        th1 = new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("We are in the first thread" + Thread.currentThread());
        System.out.println("We also begin with 3 parallel threads");

        Thread second = new Thread(new ThreadAtwo(), "second Thread");
        Thread three = new Thread(new ThreadAthree(), "third Thread");
        Thread four = new Thread(new ThreadAfour(), "fourth Thread");
        second.start();
        three.start();
        four.start();


    }
}
