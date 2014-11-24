package Funktionales;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Connector {
    public Connection connection;
    public Channel channel;
    public ConnectionFactory factory;

    public Connector() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException {
        factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setUri("amqp://test:test@b40.cz:5672"); // behint VPN anyway

        //getting a "connection" -> Thread Safe always
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        // non-autodelete, non-durable exchange
        // auto delete: if yes the exchange will delete itself after at least one queue or exchange
        // has been bound to this one, and then all queues or exchanges have been unbound.
        channel.exchangeDeclare("chat", "fanout");

    }

    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }
}
