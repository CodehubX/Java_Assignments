package AnaNew.ana;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main3 {
    private static Leutnant leutnant3;


    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {

        leutnant3 = new Leutnant(1, "Leutnant 3");

        leutnant3.ersteRunde("queue/aufgabe6Queue3");
        Thread.sleep(10000l);
        leutnant3.zweiteRunde("queue/aufgabe6Queue3", "queue/aufgabe6Queue1");
        leutnant3.analyse();

    }


}
