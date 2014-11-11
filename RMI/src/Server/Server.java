package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * java -cp /var/www/html/b40.cz/java:/var/www/html/b40.cz/java/compute.jar 
 * -Djava.rmi.server.codebase=http://b40.cz/java/compute.jar -Djava.rmi.server.hostname=b40.cz -Djava.security.policy=server.policy Server.Main  
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
