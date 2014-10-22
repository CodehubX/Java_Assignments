package Server;

import java.io.*;
import java.util.UUID;
import java.util.Vector;

/**
 * !!!!!!!!!!!Second part of the server!!!!!!!!
 * Created by jm on 10/10/2014.
 */
public class Question implements Runnable, Serializable {
    UUID id;
    String ja = "ja";
    String nein = "nein";
    String maybe = "maybe";
    String DeineAbstimmung;
    int counterJA = 0;
    int counterMAYBE = 0;
    int counterNEIN = 0;
    FileOutputStream file = null;
    DataOutputStream ds = null;
    Vector<UUID> list;

    public Question(UUID uniqueKey) throws IOException {
        file = new FileOutputStream("answers.txt");
        ds = new DataOutputStream(file);
        list = new Vector<>();
        list.add(uniqueKey);
    }

    public Question() throws FileNotFoundException {
        file = new FileOutputStream("answers.txt");
        ds = new DataOutputStream(file);
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

    public synchronized void setDeineAbstimmung(String meinungTest) throws IOException {
        //        System.out.println("Ihre Meinung zur: " + frage);
        if (meinungTest != null) {
            if (meinungTest.equals(ja)) {
                this.DeineAbstimmung = meinungTest;
                counterJA++;
                ds.writeUTF(meinungTest);
                ds.flush();
            } else if (meinungTest.equals(nein)) {
                this.DeineAbstimmung = meinungTest;
                counterNEIN++;
                ds.writeUTF(meinungTest);
                ds.flush();
            } else if (meinungTest.equals(maybe)) {
                this.DeineAbstimmung = meinungTest;
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

    @Override public void run() {
        try {
            Question qs = new Question();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
