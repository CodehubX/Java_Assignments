package newTestRabbitMW;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by jm on 10/10/2014.
 */
public class ChatClient {
    public static final String ExchangevarNameQue = "Aufgabe5Chat";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,
        KeyManagementException, URISyntaxException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://test:test@b40.cz:5672");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("my-queueEx", "topic");
        channel.queueDeclare("my-queueEx", false, false, false, null);
//        Consumer consumer;

        while (true) {
            String yourmsg = sc.next();
            channel.basicPublish("my-queueEx", "", null, yourmsg.getBytes());
//            consumer = new Consumer();
//            consumer.returnMessage();
        }


    }

}
