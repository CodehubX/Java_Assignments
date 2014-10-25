package Client;

import Server.OurServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.UUID;
import java.util.Vector;

/**
 * !!!!!!!!!!!Second part of the client!!!!!!!!
 * Created by jm on 10/10/2014.
 */
public class Question implements Serializable {
    String ja = "ja";
    String nein = "nein";
    String maybe = "maybe";
    String DeineAbstimmung;
    int counterJA = 0;
    int counterMAYBE = 0;
    int counterNEIN = 0;
    FileOutputStream file = null;
    ObjectOutputStream ds = null;
    Vector<UUID> list;
    Socket soc;
    UUID id;

    public Question(UUID uniqueKey) throws IOException {
        file = new FileOutputStream("answers.txt");
        ds = new ObjectOutputStream(file);
        list = new Vector<>();
        list.add(uniqueKey);
    }

    public Question() throws IOException {
        file = new FileOutputStream("answers.txt");
        ds = new ObjectOutputStream(file);
    }

    /**
     * This return # of people and how they voted
     *
     * @return server Abstimmung
     */
    public synchronized String getDeineAbstimmung() {
        System.out.println("How many people have provided opinion? ->");
        System.out.println("For 'ja' -> " + counterJA);
        //        BufferedInputStream bufferedInputStream = new BufferedInputStream();
        System.out.println("For 'nein' -> " + counterNEIN);
        System.out.println("For 'maybe' -> " + counterMAYBE);
        System.out.println("Anzahl der unique clients ID: " + list.size());
        return DeineAbstimmung;
    }

    /**
     * Store Cleints meinung in Object Output Stream
     *
     * @param meinungTest
     * @throws IOException
     */
    public synchronized void setDeineAbstimmung(String meinungTest) throws IOException {
        if (meinungTest != null) {
            if (meinungTest.equals(ja)) {
                this.DeineAbstimmung = meinungTest;
                System.out
                    .println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterJA++;
                ds.writeUTF(meinungTest);
                ds.flush();
            } else if (meinungTest.equals(nein)) {
                this.DeineAbstimmung = meinungTest;
                System.out
                    .println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterNEIN++;
                ds.writeUTF(meinungTest);
                ds.flush();
            } else if (meinungTest.equals(maybe)) {
                this.DeineAbstimmung = meinungTest;
                System.out
                    .println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterMAYBE++;
                ds.writeUTF(meinungTest);
                ds.flush();
            } else {
                System.out.println("Nur 'ja', 'nein' oder 'maybe' sind mogliche Antworten");
                System.out.println("Your input is wrong. Read instructions again");
            }
        } else {
            System.out.println("Repeat input- NULL");
        }
    }

    public synchronized UUID getId() {
        return id;
    }

    public synchronized void setId(UUID id) {
        this.id = id;
    }

}
