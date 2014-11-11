package Client;

import java.io.*;
import java.net.Socket;

public class ThreadPoolServer extends CounterInter implements Runnable {
    // the socket where to listen/talk
    public Socket socket;
    CounterInter ci = null;
    private ObjectInputStream sInput, ios;
    private ObjectOutputStream sOutput, oos;
    private String answerLocal; //answer

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            // create output first
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());

            oos = new ObjectOutputStream(new FileOutputStream("answers.ser"));
            ios = new ObjectInputStream(new FileInputStream("answers.ser"));

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

                //write recieved object to file
                //                oos.writeObject(ci);

                System.out.println("Client's ID is (Server Side)   " + ci.getId());
                answerLocal = ci.getAnswer();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(" Exception reading Streams:  " + id + " " + e);
                break;
            }

            try {
                oos.writeObject("\n Client on the server (Sent msg to Client in file) " + ci.getId() + " voted as " + ci.getAnswer());
                if (answerLocal.equals("ja")) {
                    counterJA++;
                } else if (answerLocal.equals("nein")) {
                    counterNEIN++;
                } else {
                    counterMAYBE++;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void close() throws IOException {
        sInput.close();
        sOutput.close();
        ios.close();
        oos.close();
    }
}
