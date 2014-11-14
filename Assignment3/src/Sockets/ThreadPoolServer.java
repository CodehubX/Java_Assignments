package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ThreadPoolServer implements Runnable {
    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;
    String answer;
    CounterInter ci;
    UUID id;
    StoreReturnValues srv;

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());

            srv = new StoreReturnValues();
            ci = new CounterInter();
        } catch (IOException e) {
            System.out.println("\nException creating new Input/Output Streams: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for clients input to write into file and console");
                answer = sInput.readUTF();
                //                System.out.println(answer);
                id = (UUID) sInput.readObject();
                //                System.out.println(id + answer);
                ci.setUUIDandAnswer(id, answer);
                System.out.println("answer and id has been saved in CI object");
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                System.out.println("Exception reading Streams (IOE or ClassNF):  " + ci.getId() + " " + e.getMessage() + " " + e.toString());
            }

            try {
                srv.store(ci);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception write Streams to file:  " + ci.getId() + " " + e.getMessage() + " " + e.toString());
            }
            System.out.println("\n Sockets is on the server says " + ci.getId() + " voted as " + ci.getAnswer());

        }
    }

}
