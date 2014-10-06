import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/*
http://syntx.io/getting-started-with-rabbitmq-in-java/
 */
public class Main {
    public Main() throws Exception {

        Cons consumer = new Cons("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Pro producer = new Pro("queue");

        for (int i = 0; i < 100000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        new Main();
    }
}
