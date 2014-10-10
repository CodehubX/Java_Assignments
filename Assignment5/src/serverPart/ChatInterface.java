package serverPart;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jm on 10/10/2014.
 */
public interface ChatInterface extends Remote {
    void postMessage(String msg) throws RemoteException;

    String getName() throws RemoteException;

    void setClient(ChatInterface c) throws RemoteException;

    ChatInterface getClient() throws RemoteException;

}
