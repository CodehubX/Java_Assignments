package Client;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Client2 {
    UUID uniqueKey;
    int port;
    boolean status;
    String server;    // for I/O

    ObjectOutputStream oos;
    ObjectInputStream ios;
    private ObjectInputStream sInput;    // to read from the socket
    private ObjectOutputStream sOutput;    // to write on the socket
    private Socket socket;


    /**
     * Constructor called by console
     *
     * @param uniqueKey
     * @param port
     */
    public Client2(UUID uniqueKey, int port) {
        this.uniqueKey = uniqueKey;
        this.port = port;
        this.server = "localhost";
    }

    public void connect() throws IOException {
        try {
            // try to connect to the server
            socket = new Socket(server, port);
            oos = new ObjectOutputStream(new FileOutputStream("answers.ser"));
            ios = new ObjectInputStream(new FileInputStream("answers.ser"));
        } catch (Exception ec) {
            // if it failed not much I can so
            display("Error connectiong to server:" + ec);
            status = false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        System.out.println("Client Connection to Server is OK! " + socket.getInetAddress() + " " + socket.getLocalPort());
        display(msg);

        // Creating both Data Stream
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            status = false;
        }

        // creates the Thread to listen from the server
        ListenFromServer listenFromServer = new ListenFromServer();
        Thread th = new Thread(listenFromServer);
        th.start();

        try {
            sOutput.writeObject(uniqueKey);
        } catch (IOException e) {
            display("Exception doing login : " + e);
            disconnect();
            status = false;
        }
        // success we inform the caller that it worked
        status = true;
    }

    /**
     * To send a message to the console
     */
    public void display(String msg) {
        System.out.println(msg);      // println in console mode
    }

    /**
     * To send a message to the server
     */
    public void sendMessage(String msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    /**
     * When something goes wrong
     * Close the Input/Output streams and disconnect not much to do in the catch clause
     */
    public void disconnect() throws IOException {
        sInput.close();
        sOutput.close();
        socket.close();
    }

    /**
     * a class that waits for the message from the server to append them in
     * System.out.println() it in console mode
     */
    class ListenFromServer implements Runnable {
        public void run() {
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    //                    String msgr = (String) ios.readObject();
                    // if console mode print the message and add back the prompt
                    System.out.println(msg);
                    System.out.print("-> ");
                } catch (IOException | ClassNotFoundException e) {
                    display("Server has close the connection: " + e);
                    break;
                }
            }
        }
    }
}
