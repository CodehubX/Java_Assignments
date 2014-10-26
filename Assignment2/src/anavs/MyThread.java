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
        	int zahl =0;
        	for (int j=0;j<4;j++){
        		
        		zahl=zahl+(A[k%4][j]*B[j][k/4]);
        		//System.out.println("C["+k%4+"]["+k/4+"]"+":  "+"A["+k%4+"]["+j+"]="+A[k%4][j]+" B["+j+"]["+k%4+"]="+B[j][k%4]+ "  "+zahl);
        	}
        	C[k%4][k/4]=zahl;
        System.out.println("Thread"+nummer+" "+"A["+k%4+"]["+k/4+"]= "+A[k%4][k/4]+"   B["+k%4+"]["+k/4+"]= "+B[k%4][k/4]+""+"   C["+k%4+"]["+k/4+"]= "+C[k%4][k/4]+"");
        
        }
    }   

}
