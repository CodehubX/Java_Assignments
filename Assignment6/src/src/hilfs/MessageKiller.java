package hilfs;

import javax.jms.*;
import javax.naming.*;

import java.util.*;
import java.text.*;

public class MessageKiller {

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
	private QueueReceiver receiver1;
	private QueueReceiver receiver2;
	private QueueReceiver receiver3;
	private TextMessage message;
	private int szenarium; 
	
	public MessageKiller() throws NamingException, JMSException {
		Context ctx = getInitialContext();
		factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		queue1 = (javax.jms.Queue) ctx.lookup(DESTINATION1);
		queue2 = (javax.jms.Queue) ctx.lookup(DESTINATION2);
		queue3 = (javax.jms.Queue) ctx.lookup(DESTINATION3);
		
	}
	public static void main(String[] args) throws NamingException, JMSException{
		MessageKiller killer=new MessageKiller();
		killer.kill();
	}
	public void kill() throws JMSException {

		connection = factory.createQueueConnection(USER, PASSWORD);
		session = connection
				.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		receiver1 = session.createReceiver(queue1);
		receiver2 = session.createReceiver(queue2);
		receiver3 = session.createReceiver(queue3);
		connection.start();
		System.out.println("connection.start()");
		
		
		while(receiver1.receive()==null){
			message=(TextMessage) receiver1.receive();	
			System.out.println(message.getText());
			System.out.println(3);
		}
		receiver1.close();
		receiver2.close();
		receiver3.close();
		session.close();
		connection.close();
		System.out.println("Ende");
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
}