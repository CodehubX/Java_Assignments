package app.mine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner inputValues = new Scanner(System.in);
        Matrix mat = new Matrix(); // also Runnable interface

        System.out.println("Enter your size of matrix A (e.g. 4 means it will be 4x4)");
        mat.setSizeA(inputValues.nextInt());
        System.out.println("You entered: "  + mat.getSizeA());
        System.out.println("Please, now enter values of the first matrix (A)");
        mat.matrixA();

        System.out.println("Enter your size of matrix B (e.g. 4 means it will be 4x4)");
        mat.setSizeB(inputValues.nextInt());
        System.out.println("You entered: "  + mat.getSizeB());
        System.out.println("Please, now enter values of the first matrix (B)");
        mat.matrixB();

        System.out.println("Now enter number of thread you want to use");
        mat.setThreads(inputValues.nextInt());


        Thread dw = new Thread(mat, "prvni");
        dw.start();

    }
    public static String startUnitTest() {
        //Main.main(new String[]{""});
        return "OK";
    }

}
