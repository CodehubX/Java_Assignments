package anavs;

import java.util.concurrent.Semaphore;

public class Thread_3  extends Thread{
private Semaphore[] sems;

public Thread_3 (Semaphore[] sems, String name){
	super(name);
	this.sems = sems;
	start();
}

private void Activity_3(){
	System.out.println("Activity_3 running");
}

public void run (){
	try {
		sems[1].acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Activity_3();
	sems[4].release();
	sems[5].release();
}
}
