package JavaByzantine;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main application container.  A command line app that runs the classic
 * Byzantine Generals problem as a group of threads.
 *
 * @author fabbri
 */
public class Byzantine {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int NUM_GENERALS, NUM_TRAITORS;

        System.out.println("how many generals and traitors ?");
        NUM_GENERALS = sc.nextInt();
        NUM_TRAITORS = sc.nextInt();

        Mission mission = new Mission(NUM_GENERALS, NUM_TRAITORS);

        ExecutorService ex = Executors.newFixedThreadPool(NUM_GENERALS);
        System.out.println("Started " + NUM_GENERALS + " generals.");

        for (int i = 0; i < NUM_GENERALS; i++) {
            General g = new General(mission);
            ex.execute(g);
        }
        System.out.println("Generals finished, exiting");
        ex.shutdown();
    }
}
