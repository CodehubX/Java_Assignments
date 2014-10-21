package novy;

import java.nio.IntBuffer;

/**
 * Created by jm on 10/19/2014.
 */
public class Main {
    public static void main(String[] args) {
        IntBuffer bd = IntBuffer.allocate(200);
        Prod d = new Prod(bd);
        Cons s = new Cons(bd);

        Thread w = new Thread(s);
        w.start();

        Thread r = new Thread(d);
        r.start();



    }
}
