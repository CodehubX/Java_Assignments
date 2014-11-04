package Client;

import java.io.Serializable;

public class Question implements Serializable {
    public String ja = "ja";
    public String nein = "nein";
    public String maybe = "maybe";
    public String DeineAbstimmung;
    public int counterJA = 0;
    public int counterMAYBE = 0;
    public int counterNEIN = 0;

    public Question(String antwort) {
        this.DeineAbstimmung = antwort;

        if (antwort.equals("ja")) {
            schickeJa(antwort);
        } else if (antwort.equals("nein")) {
            schickeNein(antwort);
        } else {
            schickeMaybe(antwort);
        }
        //        fileOu = new FileOutputStream("answers.ser");
        //        oos = new ObjectOutputStream(fileOu);
        //
        //        fileIn = new FileInputStream("answers.ser");
        //        ois = new ObjectInputStream(fileIn);
        //        file = new FileOutputStream("answers.ser");
        //        ds = new ObjectOutputStream(file);
    }
    //    ObjectOutputStream oos = null;
    //    ObjectInputStream ois = null;
    //    FileOutputStream fileOu = null;
    //    FileInputStream fileIn = null;
    //    //    BufferedReader bf;

    public String getDeineAbstimmung() {
        return DeineAbstimmung;
    }

    public void schickeJa(String msg) {
        this.ja = msg;
        counterJA++;
    }

    public void schickeNein(String msg) {
        //        this.DeineAbstimmung = msg;
        this.nein = msg;
        counterNEIN++;
    }

    public void schickeMaybe(String msg) {
        //        this.DeineAbstimmung = msg;
        this.maybe = msg;
        counterMAYBE++;
    }

    /**
     * This return # of people and how they voted
     *
     * @return server Abstimmung
     */
    public synchronized String getDeineAbstimmungPerClient() {
        System.out.println("\nHow many people have provided opinion? ->");
        System.out.println("For 'ja' -> " + counterJA);
        System.out.println("For 'nein' -> " + counterNEIN);
        System.out.println("For 'maybe' -> " + counterMAYBE);
        System.out.println("Anzahl der unique clients ID: "); // method below
        return DeineAbstimmung;
    }
    //    public String getJa() {
    //        return ja;
    //    }
    //
    //    public String getNein() {
    //        return nein;
    //    }
    //
    //    public String getMaybe() {
    //        return maybe;
    //    }



    /**
     * Store Cleints meinung in Object Output Stream
     *
     * @throws java.io.IOException public synchronized void setDeineAbstimmung(String meinung) throws IOException {
     *                             if (meinung.equals(ja)) {
     *                             status = true;
     *                             this.DeineAbstimmung = meinung;
     *                             System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
     *                             counterJA++;
     *                             oos.writeUTF(meinung);
     *                             //            oos.writeObject(meinung);
     *                             oos.flush();
     *                             //            oos.close();
     *                             } else if (meinung.equals(nein)) {
     *                             status = true;
     *                             this.DeineAbstimmung = meinung;
     *                             System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
     *                             counterNEIN++;
     *                             oos.writeUTF(meinung);
     *                             //            oos.writeObject(meinung);
     *                             oos.flush();
     *                             //            oos.close();
     *                             } else if (meinung.equals(maybe)) {
     *                             status = true;
     *                             this.DeineAbstimmung = meinung;
     *                             System.out.println("Ihre Meinung zur: " + OurServer.frage + " ist: " + DeineAbstimmung);
     *                             counterMAYBE++;
     *                             oos.writeUTF(meinung);
     *                             //            oos.writeObject(meinung);
     *                             oos.flush();
     *                             //            oos.close();
     *                             } else {
     *                             System.out.println("Nur 'ja', 'nein' oder 'maybe' sind mogliche Antworten");
     *                             System.out.println("Your input is wrong. Read instructions again");
     *                             }
     *                             }
     */

}
