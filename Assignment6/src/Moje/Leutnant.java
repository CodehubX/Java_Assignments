package Moje;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leutnant {

    public Message nachricht;
    Stack<String> post = new Stack<>();
    List<String> ergebnis = new ArrayList<>();
    private String befehl = null;

    public Leutnant()
        throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        nachricht = new Message();
    }

    public void messageBekommen() throws IOException, InterruptedException {
        //System.out.println(temp.getMessage());
        if (nachricht.getRunde() == 2 && ergebnis.isEmpty()) {
            ergebnis.add("Kein Befehl vom Commander erhalten");
            befehl = "Kein Befehl vom Commander erhalten";
        } else {//System.out.println("bin drinne");
            if (befehl == null) {
                befehl = nachricht.getNachricht();
            }
        }
        System.out.println(befehl);
        ergebnis.add(nachricht.getNachricht());

    }

    public void botenWeiterleiten() throws IOException, InterruptedException {
        System.out.println(befehl);
        nachricht.nachsteRunde();
        messageBekommen();
        nachricht.setNachricht(nachricht.getNachricht());
    }

    public String Angriff() {
        if (ergebnis.size() == 0) {
            ergebnis.add("Kein Befehl von Leutnant erhalten");
        } else {
            if (ergebnis.get(0).equals(ergebnis.get(1))) {
                return "Angriff";
            } else if (ergebnis.get(0).equals(ergebnis.get(2))) {
                return "Angriff";
            } else if (ergebnis.get(1).equals(ergebnis.get(2))) {
                return "Angriff";
            } else {
                return "Kein Angriff";
            }

        }
        return null;
    }
}
