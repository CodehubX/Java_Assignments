package Client;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterInter implements Serializable {
    int counterJA, counterMAYBE, counterNEIN;
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
        if (lbq.peek() != id) {
            lbq.put(id); // if the client votes again, then it can only be put in lbq once.
        }
        this.id = id;
        this.answer = answer;
    }

}
