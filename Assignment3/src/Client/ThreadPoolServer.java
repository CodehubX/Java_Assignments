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
            display(id + " just connected");
        } catch (IOException | ClassNotFoundException e) {
            display("Exception creating new Input/output Streams: " + e);
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
                display("input was " + cm);
            } catch (IOException | ClassNotFoundException e) {
                display(" Exception reading Streams:  " + id + " " + e);
                break;
            }
            // Switch on the type of message receive
            switch (qs.getDeineAbstimmung()) {
                case "ja":
                    try {
                        broadcast(": " + qs.counterJA);
                        writeMsg("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "nein":
                    try {
                        broadcast(": " + qs.counterNEIN);
                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "maybe":
                    try {
                        broadcast(": " + qs.counterMAYBE);
                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
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
        }
        // write the message to the stream
        try {
            sOutput.writeObject(msg);
        }
        // if an error occurs, do not abort just inform the user
        catch (IOException e) {
            display("Error sending message to " + id);
            display(e.toString());
        }
        return true;
    }

    /**
     * Display a message to the console
     */
    public void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    /**
     * to broadcast a message to all Clients
     */
    private synchronized void broadcast(String message) throws IOException {
        // add HH:mm:ss and \n to the message
        String time = sdf.format(new Date());
        String messageLf = time + " " + message + "\n";
        // display message on console
        System.out.print(messageLf);
    }
}
