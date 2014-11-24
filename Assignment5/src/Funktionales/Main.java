package Funktionales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Inspired by https://github.com/zi-yang-zhang/Simple-Java-RabbitMQ-Chat
 * Thank you
 */
public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, InterruptedException {
        User user1 = new User();
        User user2 = new User();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Message: ");
            user2.consume();
            String msg = sc.next();
            user1.publish(msg);
        }
    }
}
