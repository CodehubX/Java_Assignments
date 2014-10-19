package finalAb;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by jm on 10/17/2014.
 */
public class Consumer {
    public static void main(String[] args)
        throws RemoteException, NotBoundException, MalformedURLException {


        Interface ine = (ImplementInterface) Naming.lookup("rmi://localhost/test");

    }
}
