package anavs;

import java.util.concurrent.Semaphore;

public class EventSynchronization {

	public static void main(String[] args) {
		Semaphore[] sems = new Semaphore [9];
		for (int i =0; i<sems.length; i++){
			sems[i]=new Semaphore(0);
		}
		new Thread_2(sems, "Thread2");
		new Thread_7(sems, "Thread7");
		new Thread_1(sems, "Thread1");
		new Thread_4(sems, "Thread4");
		new Thread_6(sems, "Thread6");
		new Thread_5(sems, "Thread5");
		new Thread_3(sems, "Thread3");
		
	}

}
