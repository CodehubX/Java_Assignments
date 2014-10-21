package novy;

import java.nio.IntBuffer;
import java.rmi.RemoteException;

/**
 * Created by jm on 10/19/2014.
 */
public class Prod extends RemoteException implements Runnable {
    IntBuffer bf;

    public Prod(IntBuffer b) {
        this.bf = b;
    }

    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("produzier" + bf.put(i));
        }
    }
}
