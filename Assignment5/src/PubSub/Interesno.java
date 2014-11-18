package PubSub;

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
 * Created by jm on 11/18/2014.
 */
public class Interesno {


    public static void startPublisher() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://test:test@b40.cz:5672");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("Ex", "fanout");

        while (true) {
            String meg = sc.next();
            channel.basicPublish("testEx", "", null, meg.getBytes());
            System.out.println("[x] sent message '" + meg + "'");
        }
    }


    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException {
        startPublisher();
        //ugly jdk8
        (new Thread(() -> {
            try {
                startSubscriber();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    private static void startSubscriber() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, InterruptedException {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setUri("amqp://test:test@b40.cz:5672");

        Connection connection = cf.newConnection();
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();
        channel.exchangeDeclare("Ex", "fanout");
        channel.queueBind(queueName, "Ex", "");

        //        System.out.println("The dynamically created queue is: " + queueName);
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
