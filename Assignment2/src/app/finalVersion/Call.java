package app.finalVersion;

import java.util.concurrent.Callable;

/**
 * Created by jm on 9/8/2014.
 */
public class Call implements Callable<double[][]> {
    private int row;
    private int col;
    private double A[][];
    private double B[][];
    private double C[][];

    /**
     * @param row how many rows
     * @param col how many columns does it have
     * @param A   First Matrix
     * @param B   Second Matrix
     * @param C   Matrix to calculate
     */
    public Call(int row, int col, double[][] A, double[][] B, double C[][]) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    /**
     * Matrix calculation
     *
     * @return Matrix element which has been calculated
     * @throws Exception
     */
    @Override
    public double[][] call() throws Exception {
        //System.out.println(B.length); //4
        for (int k = 0; k < B.length; k++) {
            C[row][col] += A[row][k] * B[k][col];
        }
        /*
         * Print results step by step
         */
        System.out.println("------------ Test--");
        for (row = 0; row < B.length; row++) {
            for (col = 0; col < B.length; col++) {
                System.out.print("  " + C[row][col]);
            }
            System.out.println("");
        }
        return C;
    }
}

