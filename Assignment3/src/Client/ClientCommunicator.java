package Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ClientCommunicator {

    public CounterInter ci;
    private UUID uniqueKey;
    private String server = "localhost";    // for I/O
    private ObjectInputStream ios;    // to read from the socket
    private ObjectOutputStream sOutput;    // to write on the socket
    private Socket socket;
    private int port = 8474;

    public ClientCommunicator() {
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
            System.out.println("Error connectiong to server:" + ec);
        }

        System.out.println("Connection accepted by server " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            ios = new ObjectInputStream(new FileInputStream("answers.ser"));
            System.out.println("input/output ist ok beim Client");
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
        }

    }

    public void writeClient(String answer) throws IOException, InterruptedException {
        ci.setUUIDandAnswer(uniqueKey, answer);
        sOutput.writeObject(ci);
        //        sOutput.flush();
        System.out.println("Clients  uniqueKey & answer finally send to the server. It's " + uniqueKey + " & your answer: (" + answer + ")");
    }

    public void clientsInformation() throws IOException, ClassNotFoundException {
        //        String rep = ios.readUTF();
        //        System.out.println(rep);
        System.out.println(ci.clientsAnswer());
    }

    /**
     * When something goes wrong
     * Close the Input/Output streams and disconnect
     */
    public void disconnect() throws IOException {
        sOutput.close();
        socket.close();
    }


    //            while (true) {
    //                try {
    //                    String msg = (String) sInput.readObject();
    //                    System.out.println(" -> " + msg);
    //                } catch (IOException | ClassNotFoundException e) {
    //                    System.out.println("Server has close the connection: " + e);
    //                    break;
    //                }
    //            }

}
