package Client;

import java.io.Serializable;

/**
 * Created by jm on 10/10/2014.
 */
public class Question implements Serializable{
    String ja = "ja";
    String nein = "nein";
    String maybe = "maybe";
    String frage = "Is Mr. Obama right-wing ?";
    String abstimmung;

    public Question() {

    }

    public String getAbstimmung() {
        return abstimmung;
    }

    public void setAbstimmung(String abstimmung) {
        this.abstimmung = abstimmung;
    }
}
