package ver1;

public class Konsument {
    public static void main(String args[]) {
        try {
            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/PKP");
            System.out.println(server.entnehmen());
            System.out.println("entgenommen");
            System.out.println("Aktueller Stand: " + server.StandAnzeigen());
        } catch (Exception e) {
            System.out.println("Ausnahme: " + e);
        }

    }
}
