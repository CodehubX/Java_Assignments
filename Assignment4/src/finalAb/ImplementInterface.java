package finalAb;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by jm on 10/17/2014.
 */
public class ImplementInterface extends UnicastRemoteObject implements Interface {



    public ImplementInterface() throws RemoteException {
    }

    @Override public String Stand() {

        return "dsa";
    }
}
