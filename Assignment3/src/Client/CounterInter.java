package Client;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterInter implements Serializable {
    int counterJA;
    int counterMAYBE;
    int counterNEIN;
    String answer;
    UUID id;
    LinkedBlockingQueue<UUID> lbq;

    public CounterInter() {
        lbq = new LinkedBlockingQueue<UUID>();
    }

    public String getAnswer() {
        return answer;
    }

    public UUID getId() {
        return id;
    }

    public void setUUIDandAnswer(UUID id, String answer) throws InterruptedException {
        lbq.put(id);
        this.id = id;
        this.answer = answer;
    }

    public String clientsAnswer() {
        return "\n " + lbq.size() + " clients voted all in all as follows: \n Ja: " + this.counterJA + "\n Nein: " + counterNEIN + "\n maybe:" + counterMAYBE;
    }

    public void setCounter() {
        if (answer.equals("ja")) {
            System.out.println(counterJA+1);
        } else if (answer.equals("nein")) {
            System.out.println(counterNEIN+1);
        } else {
            System.out.println(counterMAYBE+1);
        }
    }
}
