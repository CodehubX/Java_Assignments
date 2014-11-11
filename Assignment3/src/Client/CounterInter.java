package Client;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterInter implements Serializable {
    public int counterJA = 0;
    public int counterMAYBE = 0;
    public int counterNEIN = 0;
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
        return lbq.size() + "clients\n" + " voted all in all as follows:\n Ja: " + counterJA + "\n Nein: " + counterNEIN + "\n maybe:" + counterMAYBE;
    }
}
