package Client;

import Server.OurServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by jm on 10/17/2014.
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"));
        Socket ss = new Socket("localhost", OurServer.port); // vznikne socket, ktery se pripoji na dany port a host

        UUID uniqueKey = UUID.randomUUID();
        System.out.println("Client Connection to Server is OK! " + ss.getInetAddress() + " " + ss.getLocalPort() + "\n" + OurServer.frage);

//        Question qs = new Question(uniqueKey); // ulozi UUID a pripravi object pro Q/A
        System.out.println("\nProvide your opinion or ask server for information.");

        sc.reset();
        System.out.println("Choose from the menu - 1 is for input; 2 is for information");
        try {
            int menuChoice = sc.nextInt();
            if (menuChoice == 1) {
                oos.writeInt(1);
                oos.writeObject(uniqueKey);
                //                    qs.setDeineAbstimmung(sc.next());  // we use here Scanner for input
            } else if (menuChoice == 2) {
                oos.writeInt(2);
                //                    qs.getDeineAbstimmungPerClient(); // return answers
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage() + "only number are allowed");
            sc.close(); // close scanner
        }

    }
}
