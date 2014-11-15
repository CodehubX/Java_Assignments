package Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadPoolServer implements Runnable {
    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;
    String answer;
    CounterInter ci = new CounterInter();
    StoreReturnValues srv;

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());

            srv = new StoreReturnValues();
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
                ci.setUUIDandAnswer(answer);
                System.out.println("answer and id has been saved in CI object");
            } catch (IOException | InterruptedException e) {
                System.out.println("Exception reading Streams (IOE or ClassNF):  " + " " + e.getMessage() + " " + e.toString());
            }

            try {
                srv.store(ci);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception write Streams to file:  " + " " + e.getMessage() + " " + e.toString());
            }
            System.out.println("\n Sockets is on the server voted as " + ci.getAnswer());
            try {
                sOutput.writeUTF(ci.getAnswersMap());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
