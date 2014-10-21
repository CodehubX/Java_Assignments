package Client;

import Server.OurServer;
import Server.Question;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jm on 10/17/2014.
 */
public class Client {
    static final Logger logger = LogManager.getLogger(Client.class.getName());


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket ss = new Socket("localhost", OurServer.port);
        logger.entry();

        System.out.println(
            "Client Connection to Server is OK!" + ss.getInetAddress() + ss
                .getLocalPort() + "\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ss.getOutputStream()));

        //        FileOutputStream file = new FileOutputStream("text.txt");
        //        BufferedOutputStream bos = new BufferedOutputStream(file);
        //        DataOutputStream dos = new DataOutputStream(bos);
        //        dos.writeUTF(br.readLine());
        Question qs = new Question();
        logger.exit();
        System.out.println("\nProvide your opinion or ask server for information.");

        while (true) {
            logger.entry();
            System.out.println("Choose from the menu - 1 is for input; 2 is for information");
            int menuChoice = sc.nextInt();
            if (menuChoice == 1) {
                System.out.println("your vote please here");
                qs.setDeineAbstimmung(sc.next());
            } else if (menuChoice ==2) {
                qs.getDeineAbstimmung();
            } else{
                ss.close();
                break;
            }
            logger.exit();
        }

    }


}
