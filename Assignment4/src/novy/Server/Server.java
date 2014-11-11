package novy.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class which we use to implement the interface and store Mensch in Queue
 */
public class Server extends UnicastRemoteObject implements Prod_Con_Methods {
    LinkedBlockingQueue<Mensch> lbq;

    public Server() throws RemoteException {
        super();  // required to avoid the 'rmic' step
        lbq = new LinkedBlockingQueue<Mensch>(10);
    }

    public void produce(Mensch e) throws RemoteException, InterruptedException {
        lbq.put(e);
    }

    public Mensch consume() throws RemoteException, InterruptedException {
        return lbq.take();
    }

    public int size() {
        return lbq.size();
    }
}
