package Client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ThreadPoolServer implements Runnable {
    public String ja = "ja";
    public String nein = "nein";
    public String DeineAbstimmung;
    public int counterJA = 0;
    public int counterMAYBE = 0;
    public int counterNEIN = 0;

    // the socket where to listen/talk
    Socket socket;
    ObjectInputStream sInput;
    ObjectOutputStream sOutput;
    //    Question qs;
    ObjectOutputStream oos;
    ObjectInputStream ios;
    UUID id; // uniqie ID client
    String cm; //answer
    SimpleDateFormat sdf;
    String date; // the date/time


    public ThreadPoolServer(Socket socket) throws IOException {
        this.socket = socket;
        sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("\nThread trying to create Object Input/Output Streams");
        try {
            // create output first
            sOutput = new ObjectOutputStream(socket.getOutputStream());
            sInput = new ObjectInputStream(socket.getInputStream());
            // read the ID
            id = (UUID) sInput.readObject();
            System.out.println("Client    " + id + "    has connected");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nException creating new Input/output Streams: " + e);
            return;
        }
        date = new Date().toString() + "\n";
        oos = new ObjectOutputStream(new FileOutputStream("answers.ser"));
        ios = new ObjectInputStream(new FileInputStream("answers.ser"));
    }

    public void run() {
        while (true) {
            try {
                //username = (String) sInput.readObject();
                cm = (String) sInput.readObject();
                //                String fileread = (String) ios.readObject();
                //                qs = new Question(cm);
                //                System.out.println("input was " + fileread);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(" Exception reading Streams:  " + id + " " + e);
                break;
            }

            /**
             *Switch on the type of message receive
             * writemsg writes to client
             * sout writes to server console
             * it wont be counted as one together but as each cleint unique
             */
            switch (cm) {
                case "ja":
                    counterJA++;
                    try {
                        writeMsg("\n" + id + " voted as " + cm + ": " + counterJA);
                        System.out.println("\n" + id + " voted as " + cm + ": " + counterJA);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "nein":
                    counterNEIN++;
                    try {
                        writeMsg("\n" + id + " voted as " + cm + ": " + counterNEIN);
                        System.out.println("\n" + id + " voted as " + cm + ": " + counterNEIN);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "maybe":
                    counterMAYBE++;
                    try {
                        writeMsg("\n" + id + " voted as " + cm + ": " + counterMAYBE);
                        System.out.println("\n" + id + " voted as " + cm + ": " + counterMAYBE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Statistics":
                    try {
                        AbstimmungPerClient();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        sInput.close();
        sOutput.close();
        ios.close();
        oos.close();
        socket.close();
    }

    /**
     * Write a String to the Client output stream
     */
    public void writeMsg(String msg) throws IOException {
        // if Client is still connected send the message to it
        if (!socket.isConnected()) {
            close();
        } else {
            try {
                // write the message to the stream
                sOutput.writeObject(msg);
                //                oos.writeObject(msg);
            } catch (IOException e) {
                // if an error occurs, do not abort just inform the user
                System.out.println("Error sending message to " + id);
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Display a message to the console
     *
     * @deprecated
     */
    public void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    /**
     * to broadcast a message to all Clients
     * not used anymore
     */
    private synchronized void broadcast(String message) {
        // add HH:mm:ss and \n to the message
        String time = sdf.format(new Date());
        String messageLf = time + " " + message + "\n";
        // System.out.println( message on console
        System.out.print(messageLf);
    }

    /**
     * @return message to the client console
     * @throws IOException
     */
    public synchronized String AbstimmungPerClient() throws IOException {
        writeMsg("\nHow many people have provided opinion? ->"
            + " \n" + "For 'ja' -> " + counterJA
            + " \n" + "For 'nein'->" + counterNEIN
            + " \n" + "For 'maybe' -> " + counterMAYBE);
        return "";
    }
}
