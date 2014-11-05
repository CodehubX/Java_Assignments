package Client;

import java.io.Serializable;

public class Question implements Serializable {
    public String ja = "ja";
    public String nein = "nein";
    public String DeineAbstimmung;
    public int counterJA = 0;
    public int counterMAYBE = 3;
    public int counterNEIN = 2;

    public Question(String antwort) {
        this.DeineAbstimmung = antwort;

        if (DeineAbstimmung.equals("ja")) {
            counterJA++;
        }
        if (DeineAbstimmung.equals("nein")) {
            counterNEIN++;
        }
        if (DeineAbstimmung.equals("maybe")) {
            counterMAYBE++;
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
