package ana;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main {
private static Kommander kommander;
private static Leutnant leutnant1;
private static Leutnant leutnant2;
private static Leutnant leutnant3;
private static String befehl;
private static int szenarium; 


	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		//befehl=args[0];
		//szenarium=args[1];
		befehl="Angriff";
		szenarium=4;
		
if (szenarium==1 || szenarium==4){
	//Alle Generäle arbeiten korrekt. 	
	kommander= new Kommander(szenarium,befehl);
	leutnant1 = new Leutnant(1, "Leutnant 1");
	leutnant2 = new Leutnant(1, "Leutnant 2");
	leutnant3 = new Leutnant(szenarium, "Leutnant 3");
	
	kommander.befehl();
	leutnant1.ersteRunde("queue/aufgabe6Queue1");
	leutnant2.ersteRunde("queue/aufgabe6Queue2");
	leutnant3.ersteRunde("queue/aufgabe6Queue3");
	 Thread.sleep(5000);
	 System.out.println("geschlafen");
	 
	 System.out.println(2);
	leutnant1.zweiteRunde("queue/aufgabe6Queue2", "queue/aufgabe6Queue3");
	leutnant2.zweiteRunde("queue/aufgabe6Queue3", "queue/aufgabe6Queue1");
	leutnant3.zweiteRunde("queue/aufgabe6Queue1", "queue/aufgabe6Queue2");
	
}

else if (szenarium==88){ 	
	;
}
	}

}
