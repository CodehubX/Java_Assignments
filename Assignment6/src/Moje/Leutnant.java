package Moje;

import Moje.MessageRabbitMq.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class Leutnant {

    public User nachricht;
    Stack<String> post = new Stack<>();

    ArrayList<String> dorList = new ArrayList<>();
    ArrayList<String> odeList = new ArrayList<>();

    public Leutnant()
        throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        nachricht = new User();
    }

    public void messageBekommen() throws IOException, InterruptedException, ExecutionException {
        nachricht.consume();
        System.out.println("Leutnant got: -> ");
    }

    public void botenWeiterleiten() throws IOException, InterruptedException {

    }

    public String Angriff() {

        return null;
    }
}
