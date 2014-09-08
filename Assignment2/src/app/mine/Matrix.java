package app.mine;

import java.util.Scanner;

public class Matrix implements MatrixInterface, Runnable {
    int sizeA;
    int sizeB;
    int threads;
    double[][] matrixA;
    double[][] matrixB;

    public Matrix() {
    }

    @Override
    public void setMatrixA(int sizeA) {
        this.matrixA = new double[sizeA][sizeA];
    }

    @Override
    public double[][] getMatrixA() {
        for (int row = 0; row < sizeA; row++){
            for (int col = 0; col < sizeA; col++){
                System.out.printf("%d   ", (int)matrixA[row][col]);
            }
            System.out.print("\n");
        }
        return matrixA;
    }

    @Override
    public void multiply() {
        long startTime = System.nanoTime();
        MatrixMulti str = new MatrixMulti();
        double[][] newar = str.multiply(getMatrixA(), getMatrixB());
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("--------The new One----------");

        for (int d = 0; d < newar.length;d++){
            for(int w = 0; w < newar[0].length;w++){
                System.out.printf("%d   ", (int) newar[d][w]);
            }
            System.out.print("\n");
        }
        System.out.println("duration in nano: " + estimatedTime);
    }

    public void multiplyOld() {
        System.out.println("---------The old one---------");

        long startTime = System.nanoTime();
        double sum = 0;
        double[][] newMatrix = new double[sizeA][sizeB];

        for (int c = 0; c < sizeA; c++) {
            for (int d = 0; d < sizeB; d++) {
                for (int k = 0; k < sizeB; k++) {
                    sum = sum + matrixA[c][k] * matrixB[k][d];
                }
                newMatrix[c][d] = sum;
                sum = 0;
            }
        }
        long estimatedTime = System.nanoTime() - startTime;

        for (int d = 0; d < newMatrix.length;d++){
            for(int w = 0; w < newMatrix[0].length;w++){
                System.out.printf("%d   ", (int) newMatrix[d][w]);
            }
            System.out.print("\n");
        }
        System.out.println("duration in nano: " + estimatedTime);
    }

    @Override
    public void setSizeA(int sizeA) {
        this.sizeA =sizeA;
    }

    @Override
    public int getSizeA() {
        return sizeA;
    }

    @Override
    public void setSizeB(int sizeB) {
        this.sizeB = sizeB;
    }

    @Override
    public int getSizeB() {
        return sizeB;
    }

    @Override
    public void setMatrixB(int sizeB) {
        this.matrixB = new double[sizeB][sizeB];
    }

    @Override
    public double[][] getMatrixB() {
        for (int rows = 0; rows < sizeB; rows++){
            for (int cols = 0; cols < sizeB; cols++){
                System.out.printf("%d   ", (int)matrixB[rows][cols]);
            }
            System.out.print("\n");
        }
        return matrixB;
    }

    @Override
    public void matrixA() {
        Scanner inputValuesA = new Scanner(System.in);
        setMatrixA(sizeA);
        for (int i = 0; i < sizeA; i++) {
            for (int k = 0; k < sizeA; k++) {
                matrixA[i][k]= (int)inputValuesA.nextDouble();
            }
        }
        int p = matrixA.length;
        System.out.println("lenght of matrix A: " + p);
        getMatrixA();
    }

    @Override
    public void matrixB() {
        Scanner inputValuesB = new Scanner(System.in);
        setMatrixB(sizeB);
        for (int i = 0; i < sizeB; i++) {
            for (int k = 0; k < sizeB; k++) {
                matrixB[i][k]= (int)inputValuesB.nextDouble();
            }
        }
        getMatrixB();
    }

    @Override
    public void setThreads(int threads) {
        this.threads = threads;
    }

    @Override
    public int getThreads() {
        return threads;
    }

    @Override
    public void run() {
        multiplyOld();
    }
}
