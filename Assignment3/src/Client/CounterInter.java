package Client;

import java.io.Serializable;
import java.util.UUID;

public class CounterInter implements Serializable {
    public int counterJA = 0;
    public int counterMAYBE = 0;
    public int counterNEIN = 0;
    String answer;
    UUID id;

    public CounterInter() {

    }

    public CounterInter(String answer) {
        this.answer = answer;
    }

    public void setUUIDandAnswer(UUID id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public String clientsAnswer() {
        return "Clients voted as follows: Ja: " + counterJA + "\n Nein: " + counterNEIN + "\n maybe:" + counterMAYBE;
    }
}
