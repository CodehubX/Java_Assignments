package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jm on 10/25/2014.
 */
public class ThreadPoolServer implements Runnable {
    // the socket where to listen/talk
    Socket socket;
    ObjectInputStream sInput;
    ObjectOutputStream sOutput;
    UUID id; // uniqie ID client
    String cm; //answer
    SimpleDateFormat sdf;
    String date; // the date/time
    Question qs;

    ThreadPoolServer(Socket socket) {
        this.socket = socket;
        sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("Thread trying to create Object Input/Output Streams");
        try {
            // create output first
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
            // read the ID
            id = (UUID) sInput.readObject();
            System.out.println(id + " just connected");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception creating new Input/output Streams: " + e);
            return;
        }
        date = new Date().toString() + "\n";
    }

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            try {
                //username = (String) sInput.readObject();
                cm = (String) sInput.readObject();
                qs = new Question(cm);
                //                System.out.println(("input was " + cm);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println((" Exception reading Streams:  " + id + " " + e);
                break;
            }
            // Switch on the type of message receive
            switch (qs.getDeineAbstimmung()) {
                case "ja":
                    broadcast("you answers was : " + cm);
                    //                        writeMsg("you answers was : " + cm); // aka input was
                    break;
                case "nein":
                    broadcast("you answers was : " + cm);
                    //                        writeMsg("you answers was : " + cm); // aka input was
                    break;
                case "maybe":
                    broadcast("you answers was : " + cm);
                    //                        writeMsg("you answers was : " + cm); // aka input was
                    break;
                case "Statistics":
                    //                        writeMsg(qs.getDeineAbstimmungPerClient() + id);
                    broadcast(qs.getDeineAbstimmungPerClient() + id);
            }
        }

        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        sInput.close();
        sOutput.close();
        socket.close();
    }

    /**
     * Write a String to the Client output stream
     */
    public boolean writeMsg(String msg) throws IOException {
        // if Client is still connected send the message to it
        if (!socket.isConnected()) {
            close();
            return false;
        } else {
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            } catch (IOException e) {
                // if an error occurs, do not abort just inform the user
                System.out.println(("Error sending message to " + id);
                System.out.println((e.toString());
            }
            return true;
        }
    }

    /**
     * Display a message to the console
     */
    public void System.out.println((String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    /**
     * to broadcast a message to all Clients
     * not used anymore
     */
    private synchronized void broadcast(String message) {
        // add HH:mm:ss and \n to the message
        String time = sdf.format(new Date());
        String messageLf = time + " " + message + "\n";
        // System.out.println( message on console
        System.out.print(messageLf);
    }
}
