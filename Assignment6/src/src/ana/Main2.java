package ana;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main2 {
private static Kommander kommander;
private static Leutnant leutnant1;
private static Leutnant leutnant2;
private static Leutnant leutnant3;
private static String befehl;
private static int szenarium; 


	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		

	leutnant2 = new Leutnant(1, "Leutnant 2");
	
	
	leutnant2.ersteRunde("queue/aufgabe6Queue2");
	Thread.sleep(10000l);
	leutnant2.zweiteRunde("queue/aufgabe6Queue2", "queue/aufgabe6Queue3");
	leutnant2.analyse();

}
	}
