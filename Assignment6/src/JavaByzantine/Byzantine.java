package JavaByzantine;

import java.util.ArrayList;

/**
 * Main application container.  A command line app that runs the classic
 * Byzantine Generals problem as a group of threads.
 *
 * @author fabbri
 */
public class Byzantine {

    private static final int NUM_GENERALS = 4;
    private static final int NUM_TRAITORS = 1;
    private static final boolean debug = true;

    public static final void debugPrint(String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Mission mission = new Mission(NUM_GENERALS, NUM_TRAITORS);

        ArrayList<General> generals = new ArrayList<General>();

        System.out.println("Hello.");
        for (int i = 0; i < NUM_GENERALS; i++) {
            General g = new General(mission);
            generals.add(g);
            g.start();
        }
        System.out.println("Started " + NUM_GENERALS + " generals.");

        for (General g : generals) {
            try {
                g.join();
            } catch (InterruptedException e) {

            }
        }
        System.out.println("Generals finished, exiting");
    }
}
