**Assignment #1**

> Schreiben Sie ein Multithread-Programm in Java, das die folgenden zeitlichen
> Abhängigkeiten für die Ausführungsaktivitäten der einzelnen Threads einhält.
> Dabei führt der i-te Thread die Aktivität ai (i = 1 - 7) aus (vgl. Event-Synchronisieren mit Semaphoren):
> 
![image](https://raw.githubusercontent.com/Johnmalc/Homeworks5/master/pictures/Assignment1.png)
> 
> Modellieren Sie diese nebenläufigen Aktivitäten der einzelnen Threads mit einem
> Petrinetz.



| Thread Number	| Acquire 	| Release 
|------------|-------|---------
| 1				|	0 1 2 	| \
| 2				|   3	 	| 0 	
| 3				|	4 5	   	| 1  	
| 4				|   6		| 2  	
| 5				|   7		| 3 4
| 6				|   8		| 5 6
| 7				|   \		| 7 8



