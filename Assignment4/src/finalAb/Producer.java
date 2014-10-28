package finalAb;

import java.nio.ByteBuffer;

/**
 * Created by jm on 10/17/2014.
 */
public class Producer {
    public static void main(String[] args) {
        int pe = 5;

        ByteBuffer bf = ByteBuffer.allocate(10);
        bf.putInt(5);

    }
}
