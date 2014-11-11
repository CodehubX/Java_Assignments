package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class Client2 {

    public CounterInter ci;
    private UUID uniqueKey;
    private String server = "localhost";    // for I/O
    private ObjectInputStream sInput;    // to read from the socket
    private ObjectOutputStream sOutput;    // to write on the socket
    private Socket socket;
    private int port = 8474;

    public Client2() {
        uniqueKey = UUID.randomUUID();
        ci = new CounterInter();
    }

    /**
     * Will call once ClientMain started
     *
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

        System.out.println("Connection accepted by server " + socket.getInetAddress() + ":" + socket.getPort());

        // Creating both Data Stream
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("input/output ist ok beim Client");
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

    }

    public synchronized void writeClient(String answer) throws IOException, InterruptedException {
        ci.setUUIDandAnswer(uniqueKey, answer);
        sOutput.writeObject(ci);
        sOutput.flush();
        System.out.println("Clients  uniqueKey & answer finally send to the server. It's " + uniqueKey + " & your answer: (" + answer + ")");
    }

    public String clientsInformation() throws IOException, ClassNotFoundException {
        ci = (CounterInter) sInput.readObject();
        return ci.clientsAnswer();
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
     * a class that waits for the message from the server to append them in
     * System.out.println() it in console mode
     */
    private class ListenFromServer implements Runnable {
        public void run() {
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    System.out.println(" -> " + msg);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Server has close the connection: " + e);
                    break;
                }
            }
        }
    }
}
