package PubSub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Sub {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, InterruptedException {

        ConnectionFactory cf = new ConnectionFactory();
        cf.setUri("amqp://test:test@b40.cz:5672");

        Connection connection = cf.newConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare("testEx", "fanout");

        // declare a default queue - the server will generate a unique name,
        // it will not be durable and it will be deleted if there is no consumer
        // using it
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("The dynamically created queue is: " + queueName);
        channel.queueBind(queueName, "testEx", "");
        System.out.println("[x] waiting for messages...");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
