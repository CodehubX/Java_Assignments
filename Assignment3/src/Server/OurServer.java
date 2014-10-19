package Server;

import java.io.IOException;
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
        Socket soc = ser.accept();

        ExecutorService ex = Executors.newFixedThreadPool(10);
        ex.submit(new HandleRequests());

    }
}
