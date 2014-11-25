package Moje.MessageRabbitMq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Inspired by https://github.com/zi-yang-zhang/Simple-Java-RabbitMQ-Chat
 * Thank you!
 * Each Run has both subscriber and publisher
 */
public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, KeyManagementException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        User user1 = new User();
        System.out.println("Your Name of the chat user");
        user1.setName(sc.next());
        user1.consume(); // wird neuer thread sowieso

        while (true) {
            System.out.println("Enter Message: ");
            String msg = sc.next();
            user1.publish(msg);
        }
    }

}
