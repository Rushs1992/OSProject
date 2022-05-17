package FinalProject;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class CurrentWaitTime {

	Process[] readyQueue;
	Queue<Process> test = new LinkedList<Process>();
	CurrentWaitTime(){
		
	} //end constructor
	
	public void testfucn(SimulateProcesses S) {
		
		readyQueue = new Process[S.processCount];
		for(int i = 0; i < S.processCount; i++) {
			readyQueue[i] = S.processQueue[i];
		}
		for(int j=0; j<S.processCount;j++ ) {
			test.add(readyQueue[j]);
		}
		System.out.println("Queue length: " + test.size());
		System.out.println("1st element: " + test.remove().getPID());
		System.out.println("Queue length: " + test.size());
}
}// end class
