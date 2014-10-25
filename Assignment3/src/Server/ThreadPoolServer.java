package Server;

import Client.Question;

import java.io.*;
import java.net.Socket;

/**
 * Created by jm on 10/25/2014.
 */
public class ThreadPoolServer implements Runnable {
    ObjectInputStream ois = null;
    ObjectOutputStream ous = null;
    Question qus;
    Socket soc;

    public ThreadPoolServer(Socket socket) {
        try {
            this.soc = socket;
            qus = new Question();
            ois = new ObjectInputStream(new FileInputStream("answers.ser"));
            ous = new ObjectOutputStream(new FileOutputStream("answers.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void run() {
        try {
            System.out.println(qus.getDeineAbstimmung());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Client :" + qus.getId() + " said " + ois.readUTF() + ois.readObject());
        } catch (EOFException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getLocalizedMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
