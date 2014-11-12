package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadPoolServer implements Runnable {
    // the socket where to listen/talk
    public Socket socket;
    public ObjectInputStream sInput;
    CounterInter ci;
    StoreReturnValues srv;
    //    private ObjectOutputStream sOutput;
    //    private String answerLocal; //answer

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            // create output first
            //            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
            srv= new StoreReturnValues();
        } catch (IOException e) {
            System.out.println("\nException creating new Input/Output Streams: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\nWaiting for clients input to write into file and console");
                try {
                    ci = (CounterInter) sInput.readObject();
                    System.out.println(ci.getAnswer() + " + " + ci.getId());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                srv.store(ci);

            } catch (IOException e) {
                System.out.println(" Exception reading Streams:  " + ci.getId() + " " + e);
                break;
            }

            //works
            System.out.println("\n Client is on the server says " + ci.getId() + " voted as " + ci.getAnswer());
        }
    }

}
