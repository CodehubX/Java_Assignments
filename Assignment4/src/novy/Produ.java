package novy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by jm on 11/11/2014.
 */
public class Produ {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        Mensch ms = new Mensch(5,"asd");
        Prod_Con_Methods vzdalenyPC = (Prod_Con_Methods) Naming.lookup("rmi://localhost/PKP");
        vzdalenyPC.produce(ms);
        System.out.println(ms.toString() + " current size of Server: " + vzdalenyPC.size());

    }
}
