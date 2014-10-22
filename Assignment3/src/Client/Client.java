package Client;

import Server.OurServer;
import Server.Question;

import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by jm on 10/17/2014.
 */
public class Client {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket ss = new Socket("localhost", OurServer.port);
        UUID uniqueKey = UUID.randomUUID();

        System.out.println(
            "Client Connection to Server is OK! " + ss.getInetAddress() + " " + ss
                .getLocalPort() + "\n" + OurServer.frage);

//        ObjectInputStream br = new ObjectInputStream(ss.getInputStream());
//        ObjectOutputStream bw = new ObjectOutputStream(ss.getOutputStream());

        Question qs = new Question(uniqueKey);
        System.out.println("\nProvide your opinion or ask server for information.");

        while (true) {
            System.out.println("Choose from the menu - 1 is for input; 2 is for information");
            try {
                int menuChoice = sc.nextInt();
                if (menuChoice == 1) {
                    System.out.println("your vote please here");
                    //                String str=keyboard.readLine();
                    //                doenst work; will not use \n
                    qs.setDeineAbstimmung(sc.next());

                } else if (menuChoice == 2) {
                    qs.getDeineAbstimmung();
                } else {
                    ss.close();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + "only number are allowed");
            }
        }
    }
}
