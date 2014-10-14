package auf2;

import java.util.ArrayList;

public class MyThread extends Thread{
	ArrayList <Integer>koord=new ArrayList<Integer>();
	int[][] A;
	int[][] B;
	int[][] C;
	int ii;
	int jj;
	int zahl=0;

	MyThread(int[][] A, int[][] B, int[][] C, ArrayList<Integer> koord) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.koord = koord;
		
	}

	public void run() {
		for (int k = 0; k < koord.size(); k++) {
			zahl=(Integer) koord.get(k);
			ii = zahl / 4;
			jj = zahl % 4;
			

			int c = 0;
			for (int m = 0; m < B.length; m++) {
				c += A[ii][m] * B[m][jj];
			}
			C[ii][jj] = c;
		}
	}

	public int [][] ErgebnisMatrixAusgeben(){
		return C;
		
	}
}
