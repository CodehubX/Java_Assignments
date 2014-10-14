package ver1;

import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class General3 {
	private static final String DESTINATION1 = "queue/aufgabe6Queue1";
	private static final String DESTINATION2 = "queue/aufgabe6Queue2";
	private static final String DESTINATION3 = "queue/aufgabe6Queue3";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	private String[] Werte = new String[3];
	private int i = 0;

	public General3() {
		
	}

	public void ersteRunde() throws NamingException, JMSException {

		Context ctx = getInitialContext();
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx
				.lookup("ConnectionFactory");
		Queue queue = (Queue) ctx.lookup(DESTINATION3);
		QueueConnection connection = factory.createQueueConnection(USER,
				PASSWORD);
		QueueSession session = connection.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver = session.createReceiver(queue);
		connection.start();
		System.out.println("Auf Nachricht warten ...");

		TextMessage nachricht = (TextMessage) receiver.receive();
		Werte[0] = nachricht.getText();
		System.out.println("Kommander sagt: "+Werte[0]);
		receiver.close();
		session.close();
		connection.close();
	}

	public void zweiteRundeZuGen2() throws JMSException, NamingException {
		Context ctx2 = getInitialContext();
		QueueConnectionFactory factory2 = (QueueConnectionFactory) ctx2
				.lookup("ConnectionFactory");
		Queue queue2 = (Queue) ctx2.lookup(DESTINATION3);
		QueueConnection connection2 = factory2.createQueueConnection(USER,
				PASSWORD);
		QueueSession session2 = connection2.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);
		QueueReceiver receiver2 = session2.createReceiver(queue2);
		connection2.start();
		System.out.println("Auf Nachricht warten ...");
		
		//TextMessage nachricht = (TextMessage) receiver2.receive();
		TextMessage nachricht;
		while ((nachricht = (TextMessage) receiver2.receive()) != null) {
		      
		    }
		Werte[1] = nachricht.getText();
		System.out.println("General 2 sagt: "+ Werte[2]);
	    Queue tempQueue2 = (Queue) nachricht.getJMSReplyTo();
	      TextMessage antwort = session2.createTextMessage();
	      antwort.setText(Werte[0]);
	      QueueSender sender2 = session2.createSender(tempQueue2);
	     System.out.println("Ich an General 2 schicke: "+ antwort.getText());
	     try {  sender2.send(antwort);}
	     finally{
	    	 sender2.close();
		      receiver2.close();
		      session2.close();
		      connection2.close(); 
	     }
		

	}

	public static Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.provider.url", "jnp://localhost:1099");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
		Context context = new InitialContext(props);
		return context;
	}

	public void vergleich() {
		for (int i = 0; i < 3; i++) {
			System.out.println(i + Werte[i]);
		}
	}

	public static void main(String[] args) throws Exception {
		General3 general3 = new General3();
		general3.ersteRunde();
		general3.zweiteRundeZuGen2();
		general3.vergleich();
	}
}