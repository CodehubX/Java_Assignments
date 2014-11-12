package Client;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterInter implements Serializable {
    String answer;
    UUID id;
    int counterJA, counterMAYBE, counterNEIN;

    LinkedBlockingQueue<UUID> lbq;

    public CounterInter() {
        lbq = new LinkedBlockingQueue<UUID>();
    }

    public int getCounterJA() {
        return counterJA;
    }

    public void setCounterJA(int counterJA) {
        this.counterJA = +counterJA;
    }

    public int getCounterMAYBE() {
        return counterMAYBE;
    }

    public void setCounterMAYBE(int counterMAYBE) {
        this.counterMAYBE = +counterMAYBE;
    }

    public int getCounterNEIN() {
        return counterNEIN;
    }

    public void setCounterNEIN(int counterNEIN) {
        this.counterNEIN = +counterNEIN;
    }

    public String getAnswer() {
        return answer;
    }

    public UUID getId() {
        return id;
    }

    public void setUUIDandAnswer(UUID id, String answer) throws InterruptedException {
        lbq.put(id); // if the client votes again, then it can only be put in lbq once.
        this.id = id;
        this.answer = answer;
    }

    public int sizeOfQueue() {
        return lbq.size();
    }
}
