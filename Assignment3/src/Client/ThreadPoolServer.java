package Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadPoolServer implements Runnable {
    // the socket where to listen/talk
    public Socket socket;
    boolean voted = false;
    CounterInter ci;
    private ObjectInputStream sInput, ios;
    private ObjectOutputStream sOutput, oos;
    //    private String answerLocal; //answer

    public ThreadPoolServer(Socket socket) {
        this.socket = socket;
        System.out.println("\nThreadpool created and assigned taks to do e.g. Object Input/Output Streams");
        try {
            // create output first
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(new FileOutputStream("answers.ser"));
            //            ios = new ObjectInputStream(new FileInputStream("answers.ser"));

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
                //                sInput.close();
                //write recieved object to file
                //                oos.writeObject(ci);

                //                answerLocal = ci.getAnswer();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(" Exception reading Streams:  " + ci.getId() + " " + e);
                break;
            }

            //works
            System.out.println("\n Client is on the server says " + ci.getId() + " voted as " + ci.getAnswer());
            ci.setCounter();
            //                oos.flush();
            //                oos.close(); // beinflust ob ich mehrmals antwort geben kann
        }
    }

    private void close() throws IOException {
        sInput.close();
        ios.close();
        oos.close();
    }
}
