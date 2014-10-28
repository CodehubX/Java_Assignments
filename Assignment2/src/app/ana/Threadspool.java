package auf2;

import java.util.ArrayList;



public class Threadspool {
	ArrayList<Integer> koord=new ArrayList<Integer>();
	
	int[][] A;
	int[][] B;
	int[][] C;
	int threadzahl;
	MyThread[] pool; 

	Threadspool(int[][] A, int[][] B, int[][] C, int threadzahl) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.threadzahl = threadzahl;
		pool=new MyThread[threadzahl]; 
	}

	public void Multiplikation() {
		for (int i = 0; i < threadzahl; i++) {
			int k = 0;
			for (int j = i; j < 16; j = j + threadzahl) {
				koord.add(k,j); 
				k++;
			}
			if (i == 0) {
				pool[i] = new MyThread(A, B, C, koord);

			} else {
			//	C = pool[i - 1].ErgebnisMatrixAusgeben();
				pool[i] = new MyThread(A, B, C, koord);
			}
			pool[i].run();
		koord.clear();
		}
		
		for (int i=0; i<threadzahl;i++){
			 try {
				 pool[i].join();}
					 catch(Exception e){
						 
					 }
			
		}
		ErgebnisAusgeben();
	}

	public void ErgebnisAusgeben() {
		System.out.println("A:");
		for (int m = 0; m < this.A.length; m++) {

			for (int l = 0; l < A[0].length; ++l) {
				System.out.print(A[m][l] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("B:");
		for (int m = 0; m < this.B.length; m++) {

			for (int l = 0; l < B[0].length; ++l) {
				System.out.print(B[m][l] + "\t");
			}
			System.out.println("");
		}
		
		if (this.C != null) {
			System.out.println("");
			System.out.println("C:");
			for (int m = 0; m < this.C.length; m++) {

				for (int l = 0; l < C[0].length; ++l) {
					System.out.print(C[m][l] + "\t");
				}
				System.out.println("");
			}

		}

	}
}
