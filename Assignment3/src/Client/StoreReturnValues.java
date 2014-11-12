package Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by jm on 11/12/2014.
 */
public class StoreReturnValues {
    CounterInter ci;

    public synchronized void store(CounterInter ci) throws IOException {
        this.ci = ci;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"))) {
            oos.writeObject(ci);

        }


    }
}
