package app.finalVersion;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[]) {
        int row;
        int col;
        //        int matrixA[][] = {{1, -2, 3, 4}, {-2, 3, 0, 1}, {4, -1, 2, 1}, {-2, 1, 3, -1}};
        //        int matrixB[][] = {{2, -4, -1, 1}, {-1, 1, -2, 2}, {5, 0, 3, -2}, {1, -2, 1, 0}};
        Scanner inputValues = new Scanner(System.in);
        int anzahl = Integer.valueOf(args[0]);


        System.out.println("Set the size of A matrix");
        int sizeA = inputValues.nextInt();
        double matrixA[][] = new double[sizeA][sizeA];
        System.out.println("Now set your " + sizeA * sizeA + " values");
        for (int i = 0; i < sizeA; i++) {
            for (int k = 0; k < sizeA; k++) {
                matrixA[i][k] = inputValues.nextInt();
            }
        }


        System.out.println("Set the size of B matrix");
        int sizeB = inputValues.nextInt();
        double matrixB[][] = new double[sizeB][sizeB];
        System.out.println("Now set your " + sizeB * sizeB + " values");
        for (int i = 0; i < sizeB; i++) {
            for (int k = 0; k < sizeB; k++) {
                matrixB[i][k] = inputValues.nextInt();
            }
        }

        MatrixMulti.multiply(matrixA, matrixB);

        double C[][] = new double [4][4];



        ExecutorService threadPool = Executors.newFixedThreadPool(anzahl);
        /**
         * Submit each element (position) to the threadpool to
         * E.g. it submits first element to thread pool to calculate A*B to get C
         * Then it submit another number (element) to THPL to do the same
         */
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                //Runnable wo = new Runn(row, col, A, B, C);
                threadPool.submit(new Call(row, col, matrixA, matrixB, C));
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
