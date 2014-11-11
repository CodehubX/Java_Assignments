package Server;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Eigentlich ServerMain, der die Clients selber ausfuhrt
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, RemoteException, MalformedURLException, NotBoundException {
        // create Produkt where are going to consume, produce
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Mensch ms = new Mensch(5, "Prvni");
        //Server connection
        Server server = new Server();
        // registrierung des Entfernen objekten in RMI Registry.
        //Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("PKP", server);
        // Naming.rebind("Counter", myCounter);
        System.out.println("Server started");

    }
}
