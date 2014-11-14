package Sockets;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStart {

    public static void main(String[] args) throws IOException {
        // the boolean that will be turned off to stop the server
        boolean keepGoing;
        ExecutorService ex = null;
        int port = 8474;

        String tempFile = "C:\\Users\\jm\\Documents\\Homeworks5.git\\Assignment3\\answers.ser";
        //Delete if tempFile exists
        File fileTemp = new File(tempFile);
        if (fileTemp.exists()) {
            System.out.println("Previous ser files have been deleted: " + fileTemp.delete());
        }

        keepGoing = true;

        ServerSocket ser = new ServerSocket(port);

        System.out.println("Server waiting for Clients on port " + port + ".");

        while (keepGoing) {
            Socket socket = ser.accept();    // accept connection
            System.out.println("Sockets connected to the Server - OK! But without clients ID, which will be passed with the answer");

            ThreadPoolServer t = new ThreadPoolServer(socket);  // make a thread of it
            ex = Executors.newFixedThreadPool(10);
            ex.submit(t);

            // if cannot
            if (!keepGoing) {
                break;
            }
        }

        // I was asked to stop
        try {
            ex.shutdown();
        } catch (Exception e) {
            System.out.println("Exception closing the server and clients: " + e);
        }
    }
}
