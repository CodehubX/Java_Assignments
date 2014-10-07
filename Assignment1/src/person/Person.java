package person;

/**
 * Created by jm on 10/7/2014.
 */
public class Person implements Runnable {
    int cislo;

    public Person(int cislo) {
        this.cislo = cislo;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100 ; i++) {
            cislo = cislo + 2;
        }
        System.out.println(cislo);
    }

    public int getCislo() throws InterruptedException {
        Thread.sleep(5000);
        return cislo;
    }

    public void setCislo(int cislo) {
        this.cislo = cislo;
    }
}
