package ana2;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {
    private static final String DESTINATION = "queue/myQueue1";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    private QueueConnection connection;
    private QueueSession session;
    private QueueSender sender;
    private String text;
    private long expiration;

    public Producer(String text, long expiration) throws NamingException, JMSException {

        this.text = text;
        this.expiration = expiration;

        // JNDI-Kontext erzeugen
        Context ctx = new InitialContext();
        System.out.println("Context");
        // ConnectionFactory �ber Namensdienst auslesen
        QueueConnectionFactory factory =
            (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        System.out.println("Faktory");
        // Zieladresse �ber Namensdienst auslesen
        Queue queue = (Queue) ctx.lookup(DESTINATION);

        // Verbindung aufbauen
        connection = factory.createQueueConnection(USER, PASSWORD);

        // Session erzeugen
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        // Sender erzeugen
        sender = session.createSender(queue);
    }

    public static void main(String[] args) throws Exception {
        String text = "Text";
        long expiration = Long.parseLong("8000");
        Producer producer = new Producer(text, expiration);
        producer.sendMessage();
        producer.close();
    }

    // Nachricht erzeugen und senden
    public void sendMessage() throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(text);
        sender.setTimeToLive(expiration);
        sender.send(message);
    }

    // Ressourcen freigeben
    public void close() throws JMSException {
        sender.close();
        session.close();
        connection.close();
    }
}
