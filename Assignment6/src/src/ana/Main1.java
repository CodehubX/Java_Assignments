package ana;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main1 {
private static Kommander kommander;
private static Leutnant leutnant1;
private static Leutnant leutnant2;
private static Leutnant leutnant3;
private static String befehl;
private static int szenarium; 


	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {

	leutnant1 = new Leutnant(3, "Leutnant 1");
	
	leutnant1.ersteRunde("queue/aufgabe6Queue1");
	Thread.sleep(10000l);
	leutnant1.zweiteRunde("queue/aufgabe6Queue1", "queue/aufgabe6Queue2");
	leutnant1.analyse();
	
}


}
