package app.almost;

/**
 * Created by jm on 9/8/2014.
 */
public class WorkerTh implements Runnable {
    private int row;
    private int col;
    private int A[][];
    private int B[][];
    private int C[][];

    public WorkerTh(int row, int col, int A[][], int B[][], int C[][]) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public void run() {
        for (int k = 0; k < B.length; k++) {
            C[row][col] += A[row][k] * B[k][col];
        }
        System.out.println("------------ Test--");
        for (row = 0; row < 4; row++) {
            for (col = 0; col < 4; col++) {
                System.out.print("  " + C[row][col]);
            }
            System.out.println("");
        }
    }

}

