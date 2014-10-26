package anavs;

import java.util.concurrent.Semaphore;

public class Thread_7  extends Thread{
private Semaphore[] sems;

public Thread_7 (Semaphore[] sems, String name){
	super(name);
	this.sems = sems;
	start();
}

private void Activity_7(){
	System.out.println("Activity_7 running");
}

public void run (){
	try {
		sems[7].acquire();
		sems[8].acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Activity_7();
}
}
