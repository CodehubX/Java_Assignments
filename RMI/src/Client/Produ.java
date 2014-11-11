package novy.proServer.Client;

import novy.Server.Mensch;
import novy.proServer.Server.Prod_Con_Methods;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by jm on 11/11/2014.
 */
public class Produ {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Mensch ms = new Mensch(5, "asd");
        Registry registry = LocateRegistry.getRegistry(args[0]);
        Prod_Con_Methods vzdalenyPC = (Prod_Con_Methods) registry.lookup("PKP");
        vzdalenyPC.produce(ms);
        System.out.println(ms.toString() + " current size of Server: " + vzdalenyPC.size());

    }
}
