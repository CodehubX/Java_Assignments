package novy.Client;

import novy.Server.Mensch;
import novy.Server.Prod_Con_Methods;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Produ {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Mensch ms = new Mensch(5, "asd");
        Prod_Con_Methods vzdalenyPC = (Prod_Con_Methods) Naming.lookup("rmi://b40.cz/PKP");
        vzdalenyPC.produce(ms);
        System.out.println(ms.toString() + " current size of Server: " + vzdalenyPC.size());

    }
}
