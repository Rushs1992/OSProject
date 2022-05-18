package FinalProject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CurrentWaitTime {

	Process[] readyQueue;
	ProcessNewProperties[] newReadyQueue;
	int[][] processAnalysis;
	boolean start = true;
	int currentTime = 0;
	final int timeQuantum = 3;
	int timeInCPU = 0;
	ProcessNewProperties currentProcess;
	Queue<ProcessNewProperties> running = new LinkedList<ProcessNewProperties>();
	
	CurrentWaitTime(){
		
	} //end constructor
	
	CurrentWaitTime(SimulateProcesses S){
		readyQueue = new Process[S.processCount];
		newReadyQueue = new ProcessNewProperties[S.processCount];
		processAnalysis = new int[S.processCount][5];
		for(int [] a : processAnalysis)
			Arrays.fill(a,0);
		for(int i = 0; i < S.processCount; i++) {
			readyQueue[i] = S.processQueue[i];
			newReadyQueue[i] = new ProcessNewProperties(S.processQueue[i],i);
			processAnalysis[i][0] = S.processQueue[i].getPID(); //PID
		} //end for
	}//end constructor
	
	public void scheduler() {
		if(start && (newReadyQueue.length>0)) {
			running.add(newReadyQueue[0]);
			scheduleProcess();
		}
	} //end scheduleProcess
	
	public void scheduleProcess() {
		
		while(!running.isEmpty()) {
			currentProcess = running.remove();
			if(currentProcess.setResponseTime) {
				int index = currentProcess.readyQueueIndex;
				processAnalysis[index][3] = currentTime;
				currentProcess.setResponseTime = false;
			}
			currentProcess.waitTime = 0;
			if(currentProcess.remaingBurstTime - timeQuantum > 0) {
				currentTime += timeQuantum;
				currentProcess.remaingBurstTime -= timeQuantum;
				addWaitTime(timeQuantum);
				scheduleNextProcess();
			}else {
				timeInCPU = currentProcess.remaingBurstTime;
				currentTime += timeInCPU;
				currentProcess.remaingBurstTime -= timeInCPU;
				currentProcess.completed = true;
				addWaitTime(timeInCPU);
				scheduleNextProcess();
			} //end if-else
		} //end while
		
	} //end scheduleProcess

	private void addWaitTime(int time) {
		for(int i = 0; i < newReadyQueue.length ; i++) {
			if(newReadyQueue[i].arrivalTime<= currentTime) {
				if(newReadyQueue[i].PID != currentProcess.PID && (!newReadyQueue[i].completed)) {
					newReadyQueue[i].waitTime += (time/newReadyQueue[i].priority);
					processAnalysis[i][2] += time;
				} //end if
			}else {
				break;
			} //end if-else
		} //end for
	} //end addWaitTime

	private void scheduleNextProcess() {
		int nextProcessIndex = -1;
		float maxWaitTime = -1;
		for(int i = 0; i < newReadyQueue.length ; i++) {
			if(newReadyQueue[i].arrivalTime<= currentTime) {
				if(newReadyQueue[i].PID != currentProcess.PID && (!newReadyQueue[i].completed)) {
					if(newReadyQueue[i].waitTime > maxWaitTime) {
						maxWaitTime = newReadyQueue[i].waitTime;
						nextProcessIndex = i;
					}
				} //end if
			}else {
				break;
			} //end if-else
		} //end for
		if(maxWaitTime > 0 && (nextProcessIndex >= 0)) {
			running.add(newReadyQueue[nextProcessIndex]);
		}
	} //end scheduleNextProcess


	
}// end class
