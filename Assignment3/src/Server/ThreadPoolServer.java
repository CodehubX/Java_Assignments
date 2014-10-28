package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by jm on 10/25/2014.
 */
public class ThreadPoolServer implements Runnable {
    ObjectInputStream ois = null;
    ObjectOutputStream ous = null;
    Question qus;

    Socket soc;

    public ThreadPoolServer(Socket socket) {
        try {
            this.soc = socket;
            ois = new ObjectInputStream(new FileInputStream("answers.ser"));
            //            ois = new ObjectInputStream(soc.getInputStream());

            //            ous = new ObjectOutputStream(new FileOutputStream("answers.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void run() {
        try {
            System.out.println(ois.readInt());
            if (ois.readInt() == 1) {
                System.out.println("your vote please here");
                UUID uuid = (UUID) ois.readObject();
                System.out.println(uuid);
                qus = new Question(uuid);
                qus.setDeineAbstimmung(ois.readUTF());
            } else if (ois.readInt() == 2) {
                qus.getDeineAbstimmungPerClient();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //            try {
        //                System.out.println("Client :" + /*qus.getId() +*/ " said " + ois.readUTF() + ois.readObject());
        //            } catch (IOException e) {
        //
        //            } catch (ClassNotFoundException e) {
        //            }

        //        } else {
        //            System.out.println("There are not clients at the time");
        //        }
    }
}

