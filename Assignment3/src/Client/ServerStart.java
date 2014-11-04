package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jm on 11/4/2014.
 */
public class ServerStart {
    // a unique ID for each connection
    private static UUID uniqueId;
    ExecutorService ex;
    // to display time
    private SimpleDateFormat sdf;
    // the port number to listen for connection
    private int port = 8474;
    // the boolean that will be turned of to stop the server
    public boolean keepGoing;

    /**
     * server constructor that receive the port to listen to for connection as parameter
     * in console
     */
    public ServerStart() {
        sdf = new SimpleDateFormat("HH:mm:ss");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerStart serverStart = new ServerStart();
        serverStart.start();
    }

    public void start() throws IOException {
        keepGoing = true;

        ServerSocket ser = new ServerSocket(port);
        // infinite loop to wait for connections
        while (keepGoing) {
            // format message saying we are waiting
            display("Server waiting for Clients on port " + port + ".");

            Socket socket = ser.accept();    // accept connection

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
            display("Exception closing the server and clients: " + e);
        }
    }


    /**
     * Display an event (not a message) to the console or the GUI
     */
    public void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }

}
