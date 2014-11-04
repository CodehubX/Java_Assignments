package Client;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class ClientMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        UUID uniqueKey = UUID.randomUUID();
        Client2 client = new Client2(uniqueKey, 8474);
        System.out.println("\nProvide your opinion or ask server for information.");
        client.connect();
        // loop forever for message from the user
        while (true) {
            System.out.println("please answer this question: Is he black ? Only ja, maybe, nein");
            System.out.print("> ");
            // read message from user
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("ja")) {
                client.sendMessage("ja");
            } else if (msg.equalsIgnoreCase("nein")) {
                client.sendMessage("nein");
            } else if (msg.equalsIgnoreCase("maybe")) {
                client.sendMessage("maybe");
            } else {
                System.out.println("Wrong answer, you will be disconnected");
                client.disconnect();
            }
        }
    }
}



