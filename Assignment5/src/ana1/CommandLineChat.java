package ana1;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class CommandLineChat implements javax.jms.MessageListener {
    public static final String TOPIC = "topic/aufgabe5Topic";

    public static void main(String[] args) throws NamingException, JMSException, IOException {
        if (args.length != 1) {
            System.out.println("Bitte den Username angeben.");
        } else {
            String username = args[0];
            CommandLineChat commandLineChat = new CommandLineChat();
            Context initialContext = CommandLineChat.getInitialContext();
            Topic topic = (Topic) initialContext.lookup(CommandLineChat.TOPIC);
            TopicConnectionFactory topicConnectionFactory =
                (TopicConnectionFactory) initialContext.lookup("ConnectionFactory");
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            commandLineChat.subscribe(topicConnection, topic, commandLineChat);
            commandLineChat.publisch(topicConnection, topic, username);
        }
    }

    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(TopicConnection topicConnection, Topic topic,
        CommandLineChat commandLineChat) throws JMSException {
        TopicSession subscribeSession =
            topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber topicSubscriber = subscribeSession.createSubscriber(topic);
        topicSubscriber.setMessageListener(commandLineChat);
    }

    public void publisch(TopicConnection topicConnection, Topic topic, String username)
        throws JMSException, IOException {
        TopicSession publishSession =
            topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher topicPublisher = publishSession.createPublisher(topic);
        topicConnection.start();
        BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String messageToSend = reader.readLine();
            if (messageToSend.equalsIgnoreCase("exit")) {
                topicConnection.close();
                System.exit(0);
            } else {
                TextMessage message = publishSession.createTextMessage();
                message.setText("[" + username + "]: " + messageToSend);
                topicPublisher.publish(message);
            }
        }
    }

    public static Context getInitialContext() throws NamingException {
        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.put("java.naming.provider.url", "jnp://localhost:1099");
        props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
        Context context = new InitialContext(props);
        return context;
    }


}
