package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ClientCommunicator {

    public CounterInter ci;
    public UUID uniqueKey;
    public String server = "localhost";    // for I/O
    public ObjectOutputStream sOutput;    // to write on the socket
    public Socket socket;
    public int port = 8474;
    StoreReturnValues srv = null;

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
        System.out.println(ci.clientsAnswer());
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
