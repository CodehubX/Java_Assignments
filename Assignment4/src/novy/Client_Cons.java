package novy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * WHere client consumes Mensch
 */
public class Client_Cons implements Runnable {
    Server sr;
    Prod_Con_Methods vzdalenyPC;

    public Client_Cons(Server server) throws RemoteException, NotBoundException, MalformedURLException {
        this.sr = server;
        // besorgt sich diesen Server Objekt
        // ubermittlung eines Stubs


        /**
         * this is the client which gets the reference (a proxy) to the remote object living on
         * the server and invokes its method to get a message
         */
        vzdalenyPC = (Prod_Con_Methods) Naming.lookup("rmi://localhost/PKP");
        System.out.println("Connection to the RMI was OK");
    }

    @Override public void run() {
        try {
            Mensch consument = sr.consume();
            System.out.println(consument.toString() + " current size of Server: " + sr.size());
        } catch (InterruptedException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
