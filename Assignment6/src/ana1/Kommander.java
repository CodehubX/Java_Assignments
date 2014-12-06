package ana1;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Kommander {

    private static final String DESTINATION1 = "queue/aufgabe6Queue1";
    private static final String DESTINATION2 = "queue/aufgabe6Queue2";
    private static final String DESTINATION3 = "queue/aufgabe6Queue3";
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";

    private QueueConnectionFactory factory;
    private Queue queue1;
    private Queue queue2;
    private Queue queue3;
    private QueueConnection connection;
    private QueueSession session;
    private QueueSender sender1;
    private QueueSender sender2;
    private QueueSender sender3;
    private int Szenario;
    private TextMessage message;

    public Kommander(int Szenario) throws NamingException, JMSException {
        Context ctx = getInitialContext();
        factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        queue1 = (javax.jms.Queue) ctx.lookup(DESTINATION1);
        queue2 = (javax.jms.Queue) ctx.lookup(DESTINATION2);
        queue3 = (javax.jms.Queue) ctx.lookup(DESTINATION3);
        this.Szenario = Szenario;
    }

    public static void main(String[] args) throws Exception {
        Kommander kommander = new Kommander(1);
        kommander.befehl();
        // kommander.eins();
        // kommander.vier();
        // kommander.fuenf();
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

    public void befehl() throws JMSException {

        connection = factory.createQueueConnection(USER, PASSWORD);
        session = connection
            .createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        sender1 = session.createSender(queue1);
        sender2 = session.createSender(queue2);
        sender3 = session.createSender(queue3);

        switch (Szenario) {
            case 1:
                System.out.println("Szenario Nr.1. Alle Generäle arbeiten korrekt.");
                message = session.createTextMessage();
                message.setText("Angriff");
                //sender1.setTimeToLive(10000);
                sender1.send(message);
                System.out.println("an General1: " + message.getText());
                sender1.close();
                message.setText("Angriff2");
                //sender2.setTimeToLive(10000);
                sender2.send(message);
                System.out.println("an General2: " + message.getText());
                sender2.close();
                message.setText("Angriff3");
                //sender3.setTimeToLive(10000);
                sender3.send(message);
                System.out.println("an General3: " + message.getText());
                sender3.close();
                break;
            case 2:
                System.out.println("Szenario Nr.2");

                break;
            case 3:
                System.out.println("i ist zwei");
                break;
            case 4:
                System.out.println("Szenario Nr.4");
                message = session.createTextMessage();
                message.setText("Angriff");
                sender1.setTimeToLive(10000);
                sender1.send(message);
                System.out.println("an General1: " + message.getText());
                sender1.close();

                sender2.setTimeToLive(10000);
                sender2.send(message);
                System.out.println("an General2: " + message.getText());
                sender2.close();

                message.setText(
                    "Rueckzug"); //Lügner! Schickt einen anderen Wert an den dritten General.
                sender3.setTimeToLive(10000);
                sender3.send(message);
                System.out.println("an General3: " + message.getText());
                sender3.close();
                break;
            case 5:
                System.out.println("Szenario Nr.5");
                message = session.createTextMessage();
                message.setText("Angriff");
                sender1.setTimeToLive(10000);
                sender1.send(message);
                System.out.println("an General1: " + message.getText());
                sender1.close();

                sender2.setTimeToLive(10000);
                sender2.send(message);
                System.out.println("an General2: " + message.getText());
                sender2.close();

                sender3.close();
                break;
            default:
                System.out.println("Dieses Szenario wurde nicht programmiert.");
        }

        session.close();
        connection.close();
    }
}
