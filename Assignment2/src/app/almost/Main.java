package app.almost;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[]) {
        int row;
        int col;
        int A[][] = {{1, -2, 3, 4}, {-2, 3, 0, 1}, {4, -1, 2, 1}, {-2, 1, 3, -1}};
        int B[][] = {{2, -4, -1, 1}, {-1, 1, -2, 2}, {5, 0, 3, -2}, {1, -2, 1, 0}};
        int C[][] = new int[4][4];
        int t = 0;
        int anzahl = Integer.valueOf(args[0]);

        ExecutorService threadPool = Executors.newFixedThreadPool(anzahl);

        for (int i = 0; i < anzahl; i++) {
            for (row = 0; row < 4; row++) {
                for (col = 0; col < 4; col++) {
                    Runnable wo = new WorkerTh(row, col, A, B, C);
                    threadPool.execute(wo);
                }
            }
        }
        threadPool.shutdown();

        while (!threadPool.isTerminated()) {
        }
        System.out.println("Finished all threads");

        System.out.println(" A Matrix : ");
        for (row = 0; row < 4; row++)

        {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + A[row][col]);
            }
            System.out.println();
        }

        // printing matrix B
        System.out.println(" B Matrix : ");
        for (row = 0; row < 4; row++)

        {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + B[row][col]);
            }
            System.out.println();
        }

        // printing resulting matrix C after multiplication
        System.out.println(" Resulting C Matrix : ");
        for (row = 0; row < 4; row++)

        {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + C[row][col]);
            }
            System.out.println();
        }

    }

}