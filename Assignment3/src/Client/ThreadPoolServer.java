package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadPoolServer implements Runnable {
    // the socket where to listen/talk
    public Socket socket;
    boolean voted = false;
    CounterInter ci;
    StoreReturnValues srv;
    private ObjectInputStream sInput;
    //    private ObjectOutputStream sOutput;
    //    private String answerLocal; //answer

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            // create output first
            //            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("\nException creating new Input/output Streams: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\nWaiting for clients input to write into file and console");
                // read the the object of Client and his answers
                ci = (CounterInter) sInput.readObject();
                srv.store(ci);

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(" Exception reading Streams:  " + ci.getId() + " " + e);
                break;
            }

            //works
            System.out.println("\n Client is on the server says " + ci.getId() + " voted as " + ci.getAnswer());
        }
    }

}
