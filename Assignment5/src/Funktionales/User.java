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

    public User() throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException {
        connector = new Connector();
        this.ch = connector.getChannel();
        userID = this.hashCode();
        tm = new Timestamp(Calendar.getInstance().getTime().getTime());

    }

    public void consume() throws IOException, InterruptedException {
        String queueName = ch.queueDeclare().getQueue();

        ch.exchangeDeclare("chat", "fanout");
        //        ch.queueDeclare("chatroom", false, false, false, null);
        ch.queueBind(queueName, "chat", "");

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
