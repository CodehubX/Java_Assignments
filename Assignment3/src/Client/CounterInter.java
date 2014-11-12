package Client;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterInter implements Serializable {
    int counterJA;
    int counterMAYBE;
    int counterNEIN;
    boolean voted = false;
    String answer;
    UUID id;
    LinkedBlockingQueue<UUID> lbq;

    public CounterInter() {
        lbq = new LinkedBlockingQueue<UUID>();
        counterJA = 0;
        counterMAYBE = 0;
        counterNEIN = 0;
    }

    public String getAnswer() {
        return answer;
    }

    public UUID getId() {
        return id;
    }

    public synchronized void setUUIDandAnswer(UUID id, String answer) throws InterruptedException {
        if (lbq.peek() == id) {
            lbq.put(id); // if the client votes again, then it can only be put in lbq once.
        }
        this.id = id;
        this.answer = answer;
    }

    public String clientsAnswer() {
        return "\n " + lbq.size() + " clients voted all in all as follows: \n Ja: " + counterJA + "\n Nein: " + counterNEIN + "\n maybe:" + counterMAYBE;
    }

    public void setCounter() {
        voted = true;

        if (getAnswer().equals("ja")) {
            counterJA++;
        } else if (getAnswer().equals("nein")) {
            counterNEIN++;
        } else {
            counterMAYBE++;
        }
    }
}
