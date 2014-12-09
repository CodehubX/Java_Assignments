package hilfs;

import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class LeutnantNeu {
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";
	private QueueConnectionFactory factory;
	private QueueConnection connection;
	private QueueSession session;
	// private QueueReceiver receiver;
	// private QueueReceiver receiver1;
	// private QueueReceiver receiver2;
	// private QueueSender sender1;
	// private QueueSender sender2;

	private Context ctx;
	private int szenarium;
	private String name;
	private String[] Werte = new String[3];


	public LeutnantNeu(int szenarium, String name) throws NamingException {

		this.szenarium = szenarium;
		this.name = name;

	}

	// Aktives Warten auf Nachrichten (Pull-Prinzip)
	public void ersteRunde(String DESTINATION) throws JMSException,
			NamingException {
		// JNDI-Kontext erzeugen
		ctx = getInitialContext();
		// ConnectionFactory �ber Namensdienst auslesen
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		// Zieladresse �ber Namensdienst auslesen
		Queue queue = (Queue) ctx.lookup(DESTINATION);
		// Verbindung aufbauen
		connection = factory.createQueueConnection(USER, PASSWORD);
		// Session erzeugen
		session = connection
				.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		// Empfänger erzeugen
		QueueReceiver receiver = session.createReceiver(queue);
		// Empfang von Nachrichten starten
		connection.start();
		TextMessage textMessage = (TextMessage) receiver.receive();
		Werte[0] = textMessage.getText();
		System.out.println(name + ": Nach der ersten Runde habe ich bekommen: "
				+ Werte[0]);

		receiver.close();
		session.close();
		connection.close();
	}

	public void zweiteRunde(String DESTINATION1, String DESTINATION2)
			throws JMSException, NamingException, InterruptedException {
		// JNDI-Kontext erzeugen
		ctx = getInitialContext();
		// ConnectionFactory �ber Namensdienst auslesen
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		Queue queue1 = (Queue) ctx.lookup(DESTINATION1);
		Queue queue2 = (Queue) ctx.lookup(DESTINATION2);
		QueueConnection connection = factory.createQueueConnection(USER, PASSWORD);
	    QueueSession session = connection.createQueueSession(
	      false, Session.AUTO_ACKNOWLEDGE);
	    QueueReceiver receiver = session.createReceiver(queue1);
	    connection.start();
	    System.out.println("EchoServer gestartet ...");
	    
	    while (true) {
	      TextMessage nachricht = (TextMessage) receiver.receive();
	      String text = nachricht.getText();
	      Queue tempQueue = (Queue) nachricht.getJMSReplyTo();
	      TextMessage antwort = session.createTextMessage();
	      antwort.setText(text+"zurück");
	      QueueSender sender = session.createSender(tempQueue);
	      sender.send(antwort);
	    }


//		TextMessage request2 = session.createTextMessage();
//		request2.setText(Werte[0]);
//		request2.setJMSReplyTo(tempQueue2);
//		sender2.setTimeToLive(10000);
//		sender2.send(request2);
//		System.out.println(name + "schickt in der zweiten Runde den Wert 2: "
//				+ request2.getText());
//
//		// auf Antwort warten
//		TextMessage response2 = (TextMessage) receiver2.receive();
//		Werte[2] = response2.getText();
//		System.out.println(name + " hat bekommen:" + Werte[2]);
//
//		sender2.close();
//		receiver2.close();
//		tempQueue2.delete();
//
//		session.close();
//		connection.close();
	}

	public static Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		// props.put("java.naming.provider.url",
		// "jnp://infpro52.reutlingen-university.de:1099");
		props.put("java.naming.provider.url", "jnp://localhost:1099");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
		Context context = new InitialContext(props);
		return context;
	}
}