package Moje;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Generals {
    public Message nachricht;

    public Generals() throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        nachricht = new Message();
    }

    public void bootenSenden(String l1, String l2, String l3, String l4) throws IOException {
        nachricht.setNachricht(l1);
        nachricht.setNachricht(l2);
        nachricht.setNachricht(l3);
        nachricht.setNachricht(l4);

    }
}
