package ana;

import javax.jms.*;
import javax.naming.*;

import java.util.*;
import java.text.*;

public class Kommander {

	private static final String DESTINATION1 = "queue/aufgabe6Queue1";
	private static final String DESTINATION2 = "queue/aufgabe6Queue2";
	private static final String DESTINATION3 = "queue/aufgabe6Queue3";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnectionFactory factory;
	private javax.jms.Queue queue1;
	private javax.jms.Queue queue2;
	private javax.jms.Queue queue3;
	private QueueConnection connection;
	private QueueSession session;
	private QueueSender sender1;
	private QueueSender sender2;
	private QueueSender sender3;
	private String nachricht;
	private TextMessage message;
	private int szenarium; 
	
	public Kommander(int szenarium, String nachricht) throws NamingException, JMSException {
		Context ctx = getInitialContext();
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue1 = (javax.jms.Queue) ctx.lookup(DESTINATION1);
		queue2 = (javax.jms.Queue) ctx.lookup(DESTINATION2);
		queue3 = (javax.jms.Queue) ctx.lookup(DESTINATION3);
		this.szenarium=szenarium;
		this.nachricht = nachricht;
	}

	public void befehl() throws JMSException {

		connection = factory.createQueueConnection(USER, PASSWORD);
		session = connection
				.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		sender1 = session.createSender(queue1);
		sender2 = session.createSender(queue2);
		sender3 = session.createSender(queue3);
		message = session.createTextMessage();
		
		switch (szenarium) {
		case 1:case 2:case 3:
	message.setText(nachricht);
	sender1.setTimeToLive(20000);
	sender1.send(message);
	System.out.println("an General1: " + message.getText());
	
	sender2.setTimeToLive(20000);
	sender2.send(message);
	System.out.println("an General2: " + message.getText());
	
	
	sender3.setTimeToLive(20000);
	sender3.send(message);
	System.out.println("an General3: " + message.getText());
	
	sender1.close();
	sender2.close();
	sender3.close();
	session.close();
	connection.close();
	break;
		case 4:
	message.setText(nachricht);
	sender1.setTimeToLive(20000);
	sender1.send(message);
	System.out.println("an General1: " + message.getText());
	
	sender2.setTimeToLive(20000);
	sender2.send(message);
	System.out.println("an General2: " + message.getText());
	
	message.setText("Bahnhof");
	sender3.setTimeToLive(20000);
	sender3.send(message);
	System.out.println("an General3: " + message.getText());
	sender1.close();
	sender2.close();
	sender3.close();
	session.close();
	connection.close();	
	break;
case 5:
	message.setText(nachricht);
	sender1.setTimeToLive(20000);
	sender1.send(message);
	System.out.println("an General1: " + message.getText());
	
	message.setText("Bahnhof");
	sender2.setTimeToLive(20000);
	sender2.send(message);
	System.out.println("an General2: " + message.getText());
	
	//sender3.setTimeToLive(20000);
	sender1.close();
	sender2.close();
	sender3.close();
	session.close();
	connection.close();				
break;
default:
	System.out.println("Dieses Szenarium wurde nicht programmiert.");
}
}


	public static Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		//props.put("java.naming.provider.url", "jnp://infpro52.reutlingen-university.de:1099");
		props.put("java.naming.provider.url", "jnp://localhost:1099");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
		Context context = new InitialContext(props);
		return context;
	}
	public static void main(String[] args) throws NamingException, JMSException{
		Kommander kommander= new Kommander(3,"Angriff");
		kommander.befehl();
	}
}