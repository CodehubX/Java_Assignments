package ana;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject
    implements ServerInterface {
    private static final long serialVersionUID = 1L;
    private Ringpuffer ringpuffer;

    public ServerImpl() throws RemoteException {
        super();
        ringpuffer = new Ringpuffer(10);
    }

    public Meldung einstellen() throws RemoteException {
        System.out.println("Method einstellen() called ");
        String text = "Stand bevor ich einfüge: " + ringpuffer.AktuellerStand();
        Meldung meldung = new Meldung(text);
        return ringpuffer.einfuegen(meldung);
    }

    public Meldung entnehmen() throws RemoteException {
        System.out.println("Method entnehmen() called ");
        //Object obj= ringpuffer.entfernen();
        return ringpuffer.entfernen();
    }

    public int StandAnzeigen() {
        System.out.println("Method StandAnzeigen() called ");
        return ringpuffer.AktuellerStand();

    }


}
