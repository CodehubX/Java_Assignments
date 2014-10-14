package ver1;

/* Datei FIFOPuffer.java                E. Ammann      */
/* Klasse fuer den Ringpuffer zur Benutzung durch      */
/* Produzenten und Konsumenten                         */


class Ringpuffer {
    private int in_index;
    private int out_index;
    private int groesse;
    private Meldung[] fifo;
    private Meldung meldung;

    // Konstruktormethode dieser Klasse
    Ringpuffer(int groesse) {
        if (groesse < 2) {
            groesse = 2;
        }
        this.groesse = groesse;
        fifo = new Meldung[groesse];
        in_index = out_index = 0;
    }

    // 'synchronized' Methoden

    // Element aus Puffer holen
    public synchronized Meldung entfernen() {

        while (in_index == out_index) {// bedeutet leeren Puffer
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.meldung = fifo[out_index];
        fifo[out_index] = null;
        if (out_index == groesse - 1) {
            out_index = 0;
        } else {
            out_index++;
        }
        notifyAll();
        return meldung;
    }

    // Element in Puffer stellen
    public synchronized Meldung einfuegen(Meldung sms) {
        while ((in_index + 1) % groesse == out_index) {// bedeutet vollen Puffer
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        fifo[in_index] = sms;
        if (in_index == groesse - 1) {
            in_index = 0;
        } else {
            in_index++;
        }
        notifyAll();
        return sms;
    }

    public int AktuellerStand() {
        int temp = (in_index - out_index) % groesse;
        return temp;
    }
}
