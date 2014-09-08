package app.almost;

import java.lang.management.ManagementFactory;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) {
        int row;
        int col;
        int A[][] = {{1, -2, 3, 4}, {-2, 3, 0, 1}, {4, -1, 2, 1}, {-2, 1, 3, -1}};
        int B[][] = {{2, -4, -1, 1}, {-1, 1, -2, 2}, {5, 0, 3, -2}, {1, -2, 1, 0}};
        int C[][] = new int[4][4];
        int anzahl = Integer.valueOf(args[0]);

        ExecutorService threadPool = Executors.newFixedThreadPool(anzahl);

        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                //Runnable wo = new WorkerTh(row, col, A, B, C);
                threadPool.submit(new WorkerTh(row, col, A, B, C));
                threadPool.execute(new WorkerTh(row, col, A, B, C));
            }
        }

        int m = ManagementFactory.getThreadMXBean().getThreadCount();
        System.out.println("asdf: " + m);

        System.out.println("thread count: " + Thread.activeCount());
        threadPool.shutdown();

        while (!threadPool.isTerminated()) {
        }
        System.out.println("Finished all threads");


        System.out.println(" A Matrix : ");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + A[row][col]);
            }
            System.out.println();
        }

        System.out.println(" B Matrix : ");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + B[row][col]);
            }
            System.out.println();
        }

        System.out.println(" Resulting C Matrix -----------: ");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + C[row][col]);
            }
            System.out.println();
        }

    }

}