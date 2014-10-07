package person;

/**
 * Created by jm on 10/7/2014.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Person p = new Person(5);
        Thread the = new Thread(p);
        the.start();
        the.join();
        System.out.println(p.getCislo());
        p.setCislo(10);


        System.out.println(p.getCislo());

    }
}
