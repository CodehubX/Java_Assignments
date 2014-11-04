package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jm on 11/4/2014.
 */
public class ServerStart {
    // a unique ID for each connection
    private static UUID uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ThreadPoolServer> al;
    // to display time
    private SimpleDateFormat sdf;
    // the port number to listen for connection
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;

    /**
     * server constructor that receive the port to listen to for connection as parameter
     * in console
     */
    public ServerStart() {
        this.port = 8474;
        sdf = new SimpleDateFormat("HH:mm:ss");
        // ArrayList for the Client list
        al = new ArrayList<ThreadPoolServer>();
    }

    public void start() throws IOException {
        keepGoing = true;

        ServerSocket ser = new ServerSocket(8474);
        // infinite loop to wait for connections
        while (keepGoing) {
            // format message saying we are waiting
            display("Server waiting for Clients on port " + port + ".");

            Socket socket = ser.accept();    // accept connection
            // if I was asked to stop
            if (!keepGoing) {
                break;
            }

            ThreadPoolServer t = new ThreadPoolServer(socket);  // make a thread of it
            Thread th = new Thread(t);
            al.add(t);                  // save it in the ArrayList
            th.start();
        }
        // I was asked to stop
        try {
            ser.close();
            for (int i = 0; i < al.size(); ++i) {
                ThreadPoolServer tc = al.get(i);
                try {
                    tc.sInput.close();
                    tc.sOutput.close();
                    tc.socket.close();
                } catch (IOException ioE) {
                    // not much I can do
                }
            }
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
