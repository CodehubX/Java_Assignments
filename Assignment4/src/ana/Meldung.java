package ana;

import java.io.Serializable;

public class Meldung implements Serializable {
    private String meldung;

    public Meldung() {
        this.meldung = null;
    }

    public Meldung(String meldung) {
        this.meldung = meldung;
    }
}
