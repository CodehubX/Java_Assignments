package AnaNew.ana;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Leutnant {
    private static final String USER = "guest";
    private static final String PASSWORD = "guest";
    Queue tempQueue1;
    TemporaryQueue tempQueue2;
    private QueueConnectionFactory factory;
    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;
    private QueueReceiver receiver1;
    private QueueReceiver receiver2;
    private QueueSender sender2;
    private Context ctx;
    private int szenarium;
    private String[] Werte = new String[3];
    private TextMessage request2;
    private TextMessage response2;
    private String name;

    public Leutnant(int szenarium, String name) throws NamingException {
        //		this.TimeToLive = 20000l;
        //		this.TimeToReceive = 10000l;
        this.szenarium = szenarium;
        this.name = name;

    }

    public static Context getInitialContext() throws NamingException {
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        //        props.put("java.naming.provider.url", "jnp://infpro52.reutlingen-university.de:1099");
        props.put("java.naming.provider.url", "jnp://localhost:1099");
        props.put("java.naming.factory.url.pkgs",
            "org.jboss.naming:org.jnp.interfaces");
        props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
        Context context = new InitialContext(props);
        return context;
    }

    // Aktives Warten auf Nachrichten (Pull-Prinzip)
    public void ersteRunde(String DESTINATION) throws JMSException, NamingException {
        ctx = getInitialContext();
        factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        Queue queue = (Queue) ctx.lookup(DESTINATION);
        connection = factory.createQueueConnection(USER, PASSWORD);
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        receiver = session.createReceiver(queue);
        connection.start();
        TextMessage textMessage = (TextMessage) receiver.receive(1000);
        try {
            Werte[0] = textMessage.getText();
        } catch (Exception e) {

        }
        System.out.println(name + ": Nach der ersten Runde habe ich bekommen: " + Werte[0]);

        receiver.close();
        session.close();
        connection.close();
    }

    public void zweiteRunde(String DESTINATION1, String DESTINATION2) throws JMSException, NamingException, InterruptedException {
        ctx = getInitialContext();
        factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        Queue queue1 = (Queue) ctx.lookup(DESTINATION1);
        Queue queue2 = (Queue) ctx.lookup(DESTINATION2);
        connection = factory.createQueueConnection(USER, PASSWORD);
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        tempQueue2 = session.createTemporaryQueue();

        sender2 = session.createSender(queue2);
        receiver2 = session.createReceiver(tempQueue2);
        receiver1 = session.createReceiver(queue1);
        connection.start();

        switch (szenarium) {
            case 1:
            case 4:
            case 5:
                request2 = session.createTextMessage();
                request2.setText(Werte[0]);
                request2.setJMSReplyTo(tempQueue2);
                sender2.setTimeToLive(10000);
                sender2.send(request2);
                System.out.println(name + "schickt in der zweiten Runde den Wert: " + request2.getText());
                TextMessage request1 = (TextMessage) receiver1.receive(20000);
                try {
                    Werte[1] = request1.getText();
                } catch (Exception ignored) {

                }
                System.out.println(name + " hat bekommen:" + Werte[1]);
                TextMessage response1 = session.createTextMessage();
                try {
                    response1.setText(Werte[0]);
                } catch (Exception ignored) {

                }

                Queue tempQueue1 = (Queue) request1.getJMSReplyTo();
                QueueSender sender1 = session.createSender(tempQueue1);
                sender1.send(response1);
                System.out.println(name + "schickt in der zweiten Runde den Wert: " + response1.getText());
                response2 = (TextMessage) receiver2.receive(20000);
                try {
                    Werte[2] = response2.getText();
                } catch (Exception ignored) {
                }

                System.out.println(name + " hat bekommen:" + Werte[2]);

                break;
            case 2:
                request2 = session.createTextMessage();
                request2.setText("Banane");
                request2.setJMSReplyTo(tempQueue2);
                sender2.setTimeToLive(10000);
                sender2.send(request2);
                System.out.println(name + "schickt in der zweiten Runde den Wert: "
                    + request2.getText());
                request1 = (TextMessage) receiver1.receive(20000);
                Werte[1] = request1.getText();
                System.out.println(name + " hat bekommen:" + Werte[1]);
                tempQueue1 = (Queue) request1.getJMSReplyTo();
                response1 = session.createTextMessage();
                response1.setText("Milch");
                sender1 = session.createSender(tempQueue1);
                // /sender1.send(response1);
                sender1.send(response1);
                System.out.println(name + "schickt in der zweiten Runde den Wert: "
                    + response1.getText());
                // auf Antwort warten
                response2 = (TextMessage) receiver2.receive(20000);
                Werte[2] = response2.getText();
                System.out.println(name + " hat bekommen:" + Werte[2]);

                break;
            case 3:
                request2 = session.createTextMessage();
                request2.setText(Werte[0]);
                request2.setJMSReplyTo(tempQueue2);
                sender2.setTimeToLive(10000);
                sender2.send(request2);
                System.out.println(name + "schickt in der zweiten Runde den Wert: "
                    + request2.getText());
                request1 = (TextMessage) receiver1.receive(20000);
                Werte[1] = request1.getText();
                System.out.println(name + " hat bekommen:" + Werte[1]);
                System.out.println(name + "schickt in der zweiten Runde keinen  Wert an den dritten General.");
                // auf Antwort warten
                response2 = (TextMessage) receiver2.receive(20000);
                Werte[2] = response2.getText();
                System.out.println(name + " hat bekommen:" + Werte[2]);

                break;

            default:
                System.out.println("Dieses Szenarium wurde nicht programmiert.");
        }

        sender2.close();
        receiver2.close();
        tempQueue2.delete();

        session.close();
        connection.close();
    }

    public void analyse() {
        if (Werte[0].equals(Werte[1])) {
            System.out.println("Meine Entscheidung: " + Werte[0]);
        } else if (Werte[1].equals(Werte[2])) {
            System.out.println("Meine Entscheidung: " + Werte[1]);
        } else if (Werte[2].equals(Werte[0])) {
            System.out.println("Meine Entscheidung: " + Werte[2]);
        } else {
            System.out.println("Ich kann mich nicht entscheiden");
        }
    }
}
