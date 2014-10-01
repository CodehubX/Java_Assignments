package assone;

/**
 * Created by jm on 9/24/2014.
 */
public class ThreadAthree implements Runnable {
    Thread th3;
    public ThreadAthree() {
        th3 = new Thread(this);
    }

    @Override
    public void run() {

        System.out.println("We are in the third thread" + Thread.currentThread());
        try {
            th3.sleep(6000);

            Thread five = new Thread(new ThreadAfive());
            five.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
