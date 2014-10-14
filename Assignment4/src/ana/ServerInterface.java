package ana;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    public Meldung einstellen() throws RemoteException;

    public Meldung entnehmen() throws RemoteException;

    public int StandAnzeigen() throws RemoteException;
}
