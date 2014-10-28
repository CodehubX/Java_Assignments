package Client;

import Server.OurServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

    String DeineAbstimmung = null;
    ObjectOutputStream ds = null;
    FileOutputStream file = null;

    int counterJA = 0;
    int counterMAYBE = 0;
    int counterNEIN = 0;

    Vector<UUID> list;

    public Question(UUID uniqueKey) throws IOException {
        file = new FileOutputStream("answers.ser");
        ds = new ObjectOutputStream(file);
        list = new Vector<UUID>();
        list.add(uniqueKey);
    }

    public Question() {
        list = new Vector<UUID>();

        //        file = new FileOutputStream("answers.ser");
        //        ds = new ObjectOutputStream(file);
    }

    /**
     * This return # of people and how they voted
     *
     * @return server Abstimmung
     */
    public synchronized String getDeineAbstimmung() throws IOException {
        if (DeineAbstimmung != null) {
            System.out.println("\nHow many people have provided opinion? ->");
            System.out.println("For 'ja' -> " + counterJA);
            System.out.println("For 'nein' -> " + counterNEIN);
            System.out.println("For 'maybe' -> " + counterMAYBE);
            System.out.println("Anzahl der unique clients ID: " + getSize()); // method below
        } else {
            System.out.println("DeineAbstimmung ist null");
        }
        return "test";
    }

    /**
     * Store Cleints meinung in Object Output Stream
     *
     * @param meinung meinung of the Client
     * @throws java.io.IOException
     */
    public synchronized void setDeineAbstimmung(String meinung) throws IOException {
        if (meinung != null) {
            if (meinung.equals(ja)) {
                this.DeineAbstimmung = meinung;
                System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterJA++;
                ds.writeUTF(meinung);
                ds.flush();
            } else if (meinung.equals(nein)) {
                this.DeineAbstimmung = meinung;
                System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterNEIN++;
                ds.writeUTF(meinung);
                ds.flush();
            } else if (meinung.equals(maybe)) {
                this.DeineAbstimmung = meinung;
                System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
                counterMAYBE++;
                ds.writeUTF(meinung);
                ds.flush();
            } else {
                System.out.println("Nur 'ja', 'nein' oder 'maybe' sind mogliche Antworten");
                System.out.println("Your input is wrong. Read instructions again");
            }
        } else {
            System.out.println("Repeat input- NULL");
        }
    }

    public int getSize() throws IOException {
        ds.writeInt(list.size());
        return list.size();
    }

    public UUID getId() {
        if (list.isEmpty()) {
            System.out.println("There are no clients connected to the server. ");
        } else {
            return list.elementAt(0);
        }
        return null;
    }

}
