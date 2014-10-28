package novy;

import java.nio.IntBuffer;
import java.rmi.RemoteException;

/**
 * Created by jm on 10/19/2014.
 */
public class Cons extends RemoteException implements Runnable {
    IntBuffer bf;

    public Cons(IntBuffer b) {
        this.bf = b;
    }

    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("gelesen" + bf.get());
        }
    }
}
