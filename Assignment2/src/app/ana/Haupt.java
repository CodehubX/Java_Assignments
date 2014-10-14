package auf2;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Haupt {

	public static void main(String[] args) throws Zwischen1und16Exception {
		int[][] A = { { 1, -2, 3, 4 }, { -2, 3, 0, 1 }, { 4, -1, 2, 1 },
				{ -2, 1, 3, -1 }, };
		
		int[][] B = { { 2, -4, -1, 1 }, { -1, 1, -2, 2 }, { 5, 0, 3, -2 },
				{ 1, -2, 1, 0 }, };
		int[][] C=new int[4][4];
		System.out.println("Geben Sie bitte die Zahl der arbeitenden Threads ein: ");
		  Scanner in = new Scanner(System.in); 
		  int threadsAnzahl = 0;
		 try { 
			threadsAnzahl = in.nextInt();
			if (threadsAnzahl<1||threadsAnzahl>16)
			{
				
				 throw new Zwischen1und16Exception("Die Zahlen sind nur zwischen 1 und 16 erlaubt"); 
			}
			Threadspool tp= new Threadspool(A,B,C,threadsAnzahl);
			tp.Multiplikation();
		} 
		catch (InputMismatchException e) { 
		      System.out.println("Zahl eingeben!"); 
		} 
		 catch (NegativeArraySizeException e) { 
		      System.out.println("Keine negative Zahlen sind erlaubt"); 
		} 
		
		
	}

}
