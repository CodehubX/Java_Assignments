package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jm on 10/10/2014.
 */
public class OurServer {
    public static final int port = 5147;


    public static void main(String[] args) throws IOException {
        ServerSocket ser = new ServerSocket(port);
        System.out.println("ServerSocket has been established " + " : " + ser
            .getLocalPort());
        ExecutorService ex = Executors.newFixedThreadPool(10);

//        Question qs = new Question();

        while (true) {
            Socket soc = ser.accept();
            System.out.println(
                "Socket Connection has been created" + soc.getLocalAddress() + " <: " + soc
                    .getPort());
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                String msg = br.readLine();
                if (msg != null) {
                    System.out.println(msg);
                }

            }
        }
    }
}
