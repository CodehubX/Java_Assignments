package Moje;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Moje {
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);

        System.out.println("how many generals and traitors ?");
        //        int NUM_GENERALS = sc.nextInt();
        //        int NUM_TRAITORS = sc.nextInt();

        Generals k1 = new Generals();

        Leutnant l1 = new Leutnant();
        Leutnant l2 = new Leutnant();
        Leutnant l3 = new Leutnant();
        Leutnant l4 = new Leutnant();


        try {
            System.out.println("Runde1");
            k1.bootenSenden("5", "5", "5", "5");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Runde2");
            l1.botenWeiterleiten();
            l2.botenWeiterleiten();
            l3.botenWeiterleiten();
            l4.botenWeiterleiten();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(l1.Angriff());
        System.out.println(l2.Angriff());
        System.out.println(l3.Angriff());
        System.out.println(l4.Angriff());
    }
}

