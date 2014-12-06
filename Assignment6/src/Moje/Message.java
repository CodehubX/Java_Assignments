package Moje;

import Moje.MessageRabbitMq.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Message {
    int runde = 1;
    String nachricht;
    User user;

    public Message() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        user = new User();
    }

    public void nachsteRunde() {
        runde = runde + 1;
    }

    public int getRunde() {
        return runde;
    }

    public String getNachricht() throws IOException, InterruptedException {
        user.consume();
        return nachricht;
    }

    public void setNachricht(String nachricht) throws IOException {
        this.nachricht = nachricht;
        user.publish(nachricht);
    }
}

