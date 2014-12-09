package ana;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main3 {
private static Kommander kommander;
private static Leutnant leutnant1;
private static Leutnant leutnant2;
private static Leutnant leutnant3;
private static String befehl;
private static int szenarium; 


	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
	
	leutnant3 = new Leutnant(1, "Leutnant 3");
	
	leutnant3.ersteRunde("queue/aufgabe6Queue3");
	Thread.sleep(10000l);
	 leutnant3.zweiteRunde("queue/aufgabe6Queue3", "queue/aufgabe6Queue1");
	 leutnant3.analyse();
	
}


}
