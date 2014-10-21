package Server;

import java.io.*;

/**
 * Created by jm on 10/10/2014.
 */
public class Question implements Serializable, Runnable {

    String ja = "ja";
    String nein = "nein";
    String maybe = "maybe";
    String frage = "Is Mr. Obama right-wing ?";
    String DeineAbstimmung;
    int counterJA = 0;
    int counterMAYBE = 0;
    int counterNEIN = 0;
    FileOutputStream fo = null;
    DataOutputStream ds = null;

    public Question() throws FileNotFoundException {
        fo = new FileOutputStream("da.txt");
        ds = new DataOutputStream(fo);
        System.out.println("Ihre Meinung zur:" + frage);
        System.out.println("Nur 'ja', 'nein' oder 'maybe' sind mogliche Antworten");
    }

    /**
     * This return # of people and how they voted
     * @return server Abstimmung
     */
    public String getDeineAbstimmung() {
        System.out.println("How many people have provided opinion? ->");
        System.out.println("For 'ja' ->" + counterJA);
        System.out.println("For 'nein' ->" + counterNEIN);
        System.out.println("For 'maybe' ->" + counterMAYBE);
        return DeineAbstimmung;
    }

    public synchronized void setDeineAbstimmung(String meinungTest) throws IOException {
        if (meinungTest != null) {
            if (meinungTest.equals(ja)) {
                this.DeineAbstimmung = meinungTest;
                counterJA++;
                ds.writeUTF(meinungTest);
            } else if (meinungTest.equals(nein)) {
                this.DeineAbstimmung = meinungTest;
                counterNEIN++;
                ds.writeUTF("\n" + Question.class.getName() + " "+ meinungTest);
            } else if (meinungTest.equals(maybe)) {
                this.DeineAbstimmung = meinungTest;
                counterMAYBE++;
                ds.writeUTF(meinungTest);
            } else {
                System.out.println("Your input is wrong. Read instructions again");
            }
        } else {
            System.out.println("Repeat input");
        }
    }

    @Override public void run() {

    }
}
