package Moje;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Moje {
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);

        System.out.println("how many generals and traitors ?");
        //        int NUM_GENERALS = sc.nextInt();
        //        int NUM_TRAITORS = sc.nextInt();

        String befehl = "att";
        Generals k1 = new Generals(1);
        k1.bootenSenden(befehl);

        Leutnant l1 = new Leutnant();
        l1.messageBekommen();

    }
}

