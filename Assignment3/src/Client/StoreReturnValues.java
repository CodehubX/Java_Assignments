package Client;

import java.io.*;

public class StoreReturnValues implements Serializable {
    CounterInter ci = new CounterInter();

    public synchronized void store(CounterInter ci) throws IOException, ClassNotFoundException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"))) {
            oos.writeObject(ci);
//            this.ci = returnci();
            if (ci.getAnswer().equals("ja")) {
                this.ci.counterJA++;
            }
            if (ci.answer.equals("nein")) {
                this.ci.counterNEIN++;
            } else {
                this.ci.counterMAYBE++;
            }
            System.out.println("i have stored CI object in file");
            System.out.println("I have read object from file");
            String msg = "\n " + ci.lbq.size() + " clients voted all in all as follows: \n Ja: " +
                ci.counterJA + "\n Nein: " + ci.counterNEIN + "\n Maybe:" + ci.counterMAYBE;
            System.out.println(msg);
            oos.flush();
            oos.close();
        }
    }

    public CounterInter returnci() {
        ObjectInputStream ios = null;
        try {
            ios = new ObjectInputStream(new FileInputStream("answers.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ci = (CounterInter) ios.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception to read Object from file:  " + ci.getId() + " " + e.getMessage() + " " + e.toString());
        }
        return ci;
    }
}
