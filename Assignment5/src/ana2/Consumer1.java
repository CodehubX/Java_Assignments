import javax.jms.*;
import javax.naming.*;

public class Consumer1 {
  private static final String DESTINATION = "queue/myQueue1";
  private static final String USER = "guest";
  private static final String PASSWORD = "guest";
  
  private QueueConnection connection;
  private QueueSession session;
  private QueueReceiver receiver;
  private long timeout;

  public Consumer1(long timeout) throws NamingException, JMSException {
    this.timeout = timeout;
    
    // JNDI-Kontext erzeugen
    Context ctx = new InitialContext();
    
    // ConnectionFactory �ber Namensdienst auslesen
    QueueConnectionFactory factory =
      (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
      
    // Zieladresse �ber Namensdienst auslesen
    Queue queue = (Queue) ctx.lookup(DESTINATION);
    
    // Verbindung aufbauen
    connection = factory.createQueueConnection(USER, PASSWORD);
    
    // Session erzeugen
    session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      
    // Empf�nger erzeugen
    receiver = session.createReceiver(queue);
    
    // Empfang von Nachrichten starten
    connection.start();
  }
  
  // Aktives Warten auf Nachrichten (Pull-Prinzip)
  public void receiveMessage() throws JMSException {
    Message message;
    while ((message = receiver.receive(timeout)) != null) {
      if (message instanceof TextMessage) {
        TextMessage textMessage = (TextMessage) message;
        
        
        	System.out.println(textMessage.getText());
        	
        
      }
    }
  }
  
  // Ressourcen freigeben
  public void close() throws JMSException {
    receiver.close();
    session.close();
    connection.close();
  }
  
  public static void main(String[] args) throws Exception {
    long timeout = Long.parseLong("8000");
    Consumer1 consumer = new Consumer1(timeout);
    consumer.receiveMessage();
    consumer.close();
  }
}
