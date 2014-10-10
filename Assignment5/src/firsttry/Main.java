package firsttry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/*
http://syntx.io/getting-started-with-rabbitmq-in-java/
 */
public class Main {
    public Main() throws Exception {

        Cons consumer = new Cons("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Pro producer = new Pro("queue");
        int i = 0;
        Scanner scn = new Scanner(System.in);
            while(i <100) {
                HashMap message = new HashMap();
                message.put(scn.next().trim(), i);
                producer.sendMessage(message);

            }
        consumerThread.join();
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
