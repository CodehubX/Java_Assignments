package Client;

import java.io.*;

public class StoreReturnValues implements Serializable {
    CounterInter ci = new CounterInter();

    public synchronized void store(CounterInter ci) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("answers.ser"))) {
            oos.writeObject(ci);
            this.ci = returnci();
            if (ci.getAnswer().equals("ja")) {
//                System.out.println(ci.getAnswer());
                ci.setCounterJA(1);
            }
            if (ci.getAnswer().equals("nein")) {
                ci.setCounterNEIN(1);
            }
            if (ci.getAnswer().equals("maybe")){
                ci.setCounterMAYBE(1);
            }
            System.out.println("i have stored CI object in file");
            String msg = "\n " + ci.lbq.size() + " clients voted all in all as follows: \n Ja: " +
                ci.getCounterJA() + "\n Nein: " + ci.getCounterNEIN() + "\n Maybe: " + ci.getCounterMAYBE();
            System.out.println(msg);
            oos.flush();
            oos.close();
        }
    }

    private CounterInter returnci() {
        ObjectInputStream ios = null;
        try {
            ios = new ObjectInputStream(new FileInputStream("answers.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ci = (CounterInter) ios.readObject();
            System.out.println("I have read object from file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception to read Object from file:  " + ci.getId() + " " + e.getMessage() + " " + e.toString());
        }
        return ci;
    }
}
