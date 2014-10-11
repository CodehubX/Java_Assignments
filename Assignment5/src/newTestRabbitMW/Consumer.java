package newTestRabbitMW;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by jm on 10/10/2014.
 */
public class Consumer {
    public static void main(String[] args)
        throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException,
        InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://test:test@b40.cz:5672");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Sender.varNameQue, false, false, false, null);
        Scanner sc = new Scanner(System.in);


        QueueingConsumer r = new QueueingConsumer(channel);
        channel.basicConsume(Sender.varNameQue, true, r);
        while (true) {
            QueueingConsumer.Delivery delivery = r.nextDelivery();
            String delMsg = new String(delivery.getBody());
            System.out.println(delMsg);
        }

    }
}
