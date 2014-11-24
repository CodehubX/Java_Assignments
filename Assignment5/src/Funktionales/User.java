package Funktionales;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;

public class User {
    public Connector connector;
    public Channel ch;
    public int userID;
    public Timestamp tm;

    public User() throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        connector = new Connector();
        this.ch = connector.getChannel();

        //Returns a hash code value for the object.
        userID = this.hashCode();

        //Returns the number of milliseconds represented by this Date object.
        tm = new Timestamp(Calendar.getInstance().getTime().getTime());

    }

    /**
     * @throws IOException
     * @throws InterruptedException
     * @link https://www.rabbitmq.com/tutorials/tutorial-three-java.html
     */
    public void consume() throws IOException, InterruptedException {
        // when we supply no parameters to queueDeclare() we create a non-durable, exclusive, autodelete queue with a generated name:
        String queueName = ch.queueDeclare().getQueue();

        ch.exchangeDeclare("chat", "fanout");
        //From now on the chat exchange will append messages to our generated queue.
        ch.queueBind(queueName, "chat", "");

        /**
         * is deprecated but easier to do.
         * "Convenience class: an implementation of Consumer with straightforward <b>blocking</b> semantics."
         */
        QueueingConsumer consumer = new QueueingConsumer(ch);
        ch.basicConsume(queueName, true, consumer);

        ReceiverThread receiverThread = new ReceiverThread(consumer, userID);
        Thread th = new Thread(receiverThread);
        th.start();
    }


    public void publish(String msg) throws IOException {
        msg = String.valueOf(userID) + ":" + msg;
        ch.basicPublish("chat", "", null, msg.getBytes());
    }
}
