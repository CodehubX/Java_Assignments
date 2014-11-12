package Client;

import java.io.*;

public class StoreReturnValues implements Serializable {
    CounterInter ci = new CounterInter();

    public StoreReturnValues() {
    }

    public synchronized void store(CounterInter ci) throws IOException, ClassNotFoundException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"))) {
            this.ci = returnci();
            oos.writeObject(ci);
            if (ci.answer.equals("ja")) {
                ci.counterJA++;
            }
            if (ci.answer.equals("nein")) {
                ci.counterNEIN++;
            } else {
                ci.counterMAYBE++;
            }
            System.out.println("i have stored CI object in file");
            oos.flush();

            System.out.println("I have read object from file");
            String msg = "\n " + ci.lbq.size() + " clients voted all in all as follows: \n Ja: " +
                ci.counterJA + "\n Nein: " + ci.counterNEIN + "\n Maybe:" + ci.counterMAYBE;

            System.out.println(msg);
        }
    }

    public CounterInter returnci() throws IOException, ClassNotFoundException {
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream("answers.ser"));
        ci = (CounterInter) ios.readObject();
        return ci;
    }
}
