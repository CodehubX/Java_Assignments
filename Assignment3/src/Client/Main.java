package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jm on 10/17/2014.
 */
public class Main {
    public static final int port = 5178;

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(port);
        Socket socket = ss.accept();

        while(true){
            System.out.println("Server Connection is OK!");
            Question antwort = new Question();

            antwort.setAbstimmung("ja");
        }


    }
}
