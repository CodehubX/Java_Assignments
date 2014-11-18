package PubSub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Pub {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setUri("amqp://test:test@b40.cz:5672");

        Connection connection = cf.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("testEx", "direct");
        String messageToPublish = "Hello Georgina";
        channel.basicPublish("testEx", "", null, messageToPublish.getBytes());
        System.out.println("[x] sent message '" + messageToPublish + "'");
        channel.close();
        connection.close();
    }
}
