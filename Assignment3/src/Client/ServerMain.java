package Client;

import java.io.IOException;

/**
 * Created by jm on 10/10/2014.
 */
public class ServerMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerStart serverStart = new ServerStart();
        serverStart.start();

    }
}
