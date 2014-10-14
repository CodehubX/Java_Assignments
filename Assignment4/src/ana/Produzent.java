package ver1;

import java.rmi.Naming;

public class Produzent {
    public static void main(String args[]) {

        try {
            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/PKP");
            System.out.println(server.einstellen());
            System.out.println("eingestellt");
            System.out.println("Aktueller Stand: " + server.StandAnzeigen());
        } catch (Exception e) {
            System.out.println("Ausnahme: " + e);
        }
    }
}
