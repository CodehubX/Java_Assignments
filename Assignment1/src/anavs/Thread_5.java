package anavs;

import java.util.concurrent.Semaphore;

public class Thread_5  extends Thread{
private Semaphore[] sems;

public Thread_5 (Semaphore[] sems, String name){
	super(name);
	this.sems = sems;
	start();
}

private void Activity_5(){
	System.out.println("Activity_5 running");
}

public void run (){
	try {
		sems[3].acquire();
		sems[4].acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Activity_5();
	sems[7].release();
}
}
