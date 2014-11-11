
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Client where Mensch is produced
 */
public class Cleint_Prod implements Runnable {
    Mensch ms;
    Prod_Con_Methods vzdalenyPC;

    public Cleint_Prod(Mensch ms) throws RemoteException, NotBoundException, MalformedURLException {
        this.ms = ms;
        vzdalenyPC = (Prod_Con_Methods) Naming.lookup("rmi://localhost/PKP");
        System.out.println("Connection to the RMI was OK");
    }


    @Override public void run() {
        try {
            vzdalenyPC.produce(ms);
            System.out.println("Mensch produced " + " current size of Server: " + vzdalenyPC.size());
        } catch (InterruptedException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
