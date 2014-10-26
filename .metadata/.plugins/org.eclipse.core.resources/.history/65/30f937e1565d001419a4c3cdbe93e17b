package anavs;

public class MyThread extends Thread {
	int nummer;
	int anzahl;
	int A[][];
	int B[][];
	int C[][];
	
	MyThread(int nummer, int anzahl, int A[][], int B[][], int C[][]){
		this.nummer=nummer;
		this.anzahl=anzahl;
		this.A=A;
		this.B=B;
		this.C=C;
	}
    
    public void run() {
        for (int k=nummer;k<16;k=k+anzahl){
        	
        System.out.println("Thread"+nummer+" "+"A["+k%4+"]["+k/4+"]");
        System.out.println(A[k%4][k/4]);
        C[k%4][k/4]=nummer;	
        }
    }   

}
