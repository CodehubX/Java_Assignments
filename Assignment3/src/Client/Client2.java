package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class Client2 {

    private UUID uniqueKey;
    private String server = "localhost";    // for I/O

    private ObjectInputStream sInput;    // to read from the socket
    private ObjectOutputStream sOutput;    // to write on the socket
    private Socket socket;
    private int port = 8474;

    /**
     * Constructor called
     * @param uniqueKey id
     */
    public Client2(UUID uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * Will call once ClientMain started
     * @throws IOException
     */
    public void connect() throws IOException {
        try {
            // try to connect to the server
            socket = new Socket(server, port);
        } catch (Exception ec) {
            // if it failed not much I can so
            System.out.println("Error connectiong to server:" + ec);
        }

        System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
        System.out.println("Client Connection to Server is OK! " + socket.getInetAddress() + " " + socket.getLocalPort());

        // Creating both Data Stream
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("sinput/output ist ok beim Client");
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
        }

        /*
         * creates the Thread to listen from the server
         * e.g. clients
         */
        ListenFromServer listenFromServer = new ListenFromServer();
        Thread th = new Thread(listenFromServer);
        th.start();

        try {
            sOutput.writeObject(uniqueKey);
            System.out.println("soutput uniqueKey written");
        } catch (IOException e) {
            System.out.println("Exception doing login : " + e);
            disconnect();
        }

    }

    /**
     * When something goes wrong
     * Close the Input/Output streams and disconnect
     */
    public void disconnect() throws IOException {
        sInput.close();
        sOutput.close();
        socket.close();
    }

    /**
     * To send a message to the server and file
     * For the ClientMain
     */
    public synchronized void sendMessage(String msg) throws IOException {
        sOutput.writeObject(msg);
    }


    /**
     * a class that waits for the message from the server to append them in
     * System.out.println() it in console mode
     */
    private class ListenFromServer implements Runnable {
        public void run() {
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    // if console mode print the message and add back the prompt
                    System.out.println(" -> " + msg);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Server has close the connection: " + e);
                    break;
                }
            }
        }
    }
}
