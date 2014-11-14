package Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ClientCommunicator {

    public UUID uniqueKey;
    public String server = "localhost";    // for I/O
    public ObjectOutputStream sOutput;
    //    public ObjectInputStream sInput;    // to write on the socket
    public Socket socket;
    public int port = 8474;

    public ClientCommunicator() {
        uniqueKey = UUID.randomUUID();
    }

    public void connect() throws IOException {
        try {
            socket = new Socket(server, port);
        } catch (Exception ec) {
            System.out.println("Error connectiong to server:" + ec);
        }

        System.out.println("Connection accepted by server " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            //            sInput = new ObjectInputStream(socket.getInputStream());
            System.out.println("Input/Output ist ok beim Client");
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
        }

    }

    public void writeClient(String answer) throws IOException, InterruptedException {
        sOutput.writeUTF(answer);
        sOutput.writeObject(uniqueKey);
        sOutput.flush();
        //        sOutput.close();
        System.out.println("Clients  uniqueKey & answer finally send to the server. It's " + uniqueKey + " & your answer: (" + answer + ")");
    }

    public void clientsInformation() throws IOException, ClassNotFoundException {
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream("answers.ser"));
        CounterInter msg = (CounterInter) ios.readObject();
        System.out.println(msg.sizeOfQueue() + msg.getCounterJA() + msg.getAnswer());
    }
}
