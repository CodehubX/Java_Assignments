package app.finalVersion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[]) {
        int row;
        int col;
        int A[][] = {{1, -2, 3, 4}, {-2, 3, 0, 1}, {4, -1, 2, 1}, {-2, 1, 3, -1}};
        int B[][] = {{2, -4, -1, 1}, {-1, 1, -2, 2}, {5, 0, 3, -2}, {1, -2, 1, 0}};
        int C[][] = new int[4][4];
        int anzahl = Integer.valueOf(args[0]);

        ExecutorService threadPool = Executors.newFixedThreadPool(anzahl);
        /**
         * Submit each element (position) to the threadpool to
         * E.g. it submits first element to thread pool to calculate A*B to get C
         * Then it submit another number (element) to THPL to do the same
         */
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                //Runnable wo = new Runn(row, col, A, B, C);
                threadPool.submit(new Call(row, col, A, B, C));
                //threadPool.submit(new Runn(row, col, A, B, C));
            }
        }


        System.out.println("thread count: " + Thread.activeCount());
        threadPool.shutdown();


        /**
         * Final Matrix C
         */
        try {
            Thread.sleep(3000);

            System.out.println(" Resulting C Matrix -----------: ");
            for (row = 0; row < 4; row++) {
                for (col = 0; col < 4; col++) {
                    System.out.print("  " + C[row][col]);
                }
                System.out.println();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * Print matrix A from the input
         */
        /*
        System.out.println(" A Matrix : ");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + A[row][col]);
            }
            System.out.println();
        }*/

        /**
         * Print matrix B from input
         */
        /*
        System.out.println(" B Matrix : ");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + B[row][col]);
            }
            System.out.println();
        }
        */

    }

}
