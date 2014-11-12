package Client;

import java.io.*;

/**
 * Created by jm on 11/12/2014.
 */
public class StoreReturnValues {
    CounterInter ci;

    public synchronized void store(CounterInter ci) throws IOException {
        this.ci = ci;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"))) {
            oos.writeObject(ci);

            if (ci.getAnswer().equals("ja")) {
                this.ci.counterJA++;
            } else if (ci.getAnswer().equals("nein")) {
                this.ci.counterNEIN++;
            } else {
                this.ci.counterMAYBE++;
            }
        }

    }

    public CounterInter returnci() throws IOException {
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream("answers.ser"))) {
            


        }
            return ci;
    }
}
