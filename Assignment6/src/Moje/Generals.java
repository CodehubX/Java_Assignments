package Moje;

import Moje.MessageRabbitMq.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Generals {
    public User nachricht;
    private int szenarium;
    private String befehl;

    public Generals(int i) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        this.szenarium = i;
        nachricht = new User();
    }

    public void bootenSenden(String befehl) throws IOException {
        this.befehl = befehl;
        System.out.println("message from general was : -> " + befehl);
        nachricht.publish(befehl);
    }
}
