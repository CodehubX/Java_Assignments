package Funktionales;

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
        User user1 = new User();
        User user2 = new User();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Message: ");
            user2.consume();
            String msg = sc.next();
            user1.publish(msg);
        }
    }
}
