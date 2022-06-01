package FinalProject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CurrentWaitTime {

	int processCount = 0;
	Process[] readyQueue;
	ProcessNewProperties[] newReadyQueue;
	int[][] processAnalysis;
	boolean start = true;
	int currentTime = 0;
	final int timeQuantum = 3;
	int timeInCPU = 0;
	ProcessNewProperties currentProcess;
	Queue<ProcessNewProperties> running = new LinkedList<ProcessNewProperties>();
	
	double multiplyingFactor;
	
	CurrentWaitTime(SimulateProcesses S, double mFactor){
		multiplyingFactor = mFactor;
		readyQueue = new Process[S.processCount];
		newReadyQueue = new ProcessNewProperties[S.processCount];
		processAnalysis = new int[S.processCount][5];
		for(int [] a : processAnalysis)
			Arrays.fill(a,0);
		for(int i = 0; i < S.processCount; i++) {
			readyQueue[i] = S.processQueue[i];
			newReadyQueue[i] = new ProcessNewProperties(S.processQueue[i],i);
			processAnalysis[i][0] = S.processQueue[i].getPID(); //PID
			processCount++;
		} //end for
	}//end constructor
	
	public void scheduler() {
		if(start && (newReadyQueue.length>0)) {
			running.add(newReadyQueue[0]);
			scheduleProcess();
			start = false;
		}
	} //end scheduleProcess
	
	public void scheduleProcess() {
		
		while(!running.isEmpty()) {
			currentProcess = running.remove();
			currentProcess.waitTime = 0;
			if(currentProcess.remaingBurstTime - timeQuantum > 0) {
				setResponseTime();
				currentTime += timeQuantum;
				currentProcess.remaingBurstTime -= timeQuantum;
				addWaitTime(timeQuantum);
				scheduleNextProcess();
			}else {
				timeInCPU = currentProcess.remaingBurstTime;
				setResponseTime();
				currentTime += timeInCPU;
				currentProcess.remaingBurstTime -= timeInCPU;
				addWaitTime(timeInCPU);
				int index = currentProcess.readyQueueIndex;
				processAnalysis[index][1] = currentTime;
				processAnalysis[index][4] = processAnalysis[index][1] - currentProcess.arrivalTime;
				currentProcess.completed = true;
				scheduleNextProcess();
			} //end if-else
		} //end while
		
	} //end scheduleProcess

	private void addWaitTime(int time) {
		for(int i = 0; i < newReadyQueue.length ; i++) {
			if(newReadyQueue[i].arrivalTime< currentTime) {
				if(newReadyQueue[i].PID != currentProcess.PID && (!newReadyQueue[i].completed)) {
					if(newReadyQueue[i].firstWaitTime) {
						newReadyQueue[i].waitTime += ((currentTime - newReadyQueue[i].arrivalTime)*(1.0));
						newReadyQueue[i].firstWaitTime = false;
						processAnalysis[i][2] += (currentTime - newReadyQueue[i].arrivalTime);
					}else {
						newReadyQueue[i].waitTime += time;
						processAnalysis[i][2] += time;
					} // end inner if-else
					if(newReadyQueue[i].priority >1) {
						newReadyQueue[i].waitTime *= (1-(newReadyQueue[i].priority*multiplyingFactor));
					}
				} //end if
			}else {
				break;
			} //end if-else
		} //end for
		//printAnalysis();
	} //end addWaitTime

	private void scheduleNextProcess() {
		int nextProcessIndex = -1;
		float maxWaitTime = -1;
		for(int i = 0; i < newReadyQueue.length ; i++) {
			if(newReadyQueue[i].arrivalTime<= currentTime) {
				if((!newReadyQueue[i].completed)) {
					if(newReadyQueue[i].waitTime > maxWaitTime) {
						maxWaitTime = newReadyQueue[i].waitTime;
						nextProcessIndex = i;
					}
				} //end if
			}else {
				break;
			} //end if-else
		} //end for
		if(maxWaitTime >= 0 && (nextProcessIndex >= 0)) {
			running.add(newReadyQueue[nextProcessIndex]);
		}
	} //end scheduleNextProcess
	
	private void setResponseTime() {
		if(currentProcess.setResponseTime) {
			int index = currentProcess.readyQueueIndex;
			processAnalysis[index][3] = currentTime - newReadyQueue[index].arrivalTime;
			currentProcess.setResponseTime = false;
		}
	}
	
	public void printAnalysis() {
		/*
		System.out.println("\n Current Wait Time Algorithm\n");
		System.out.println("\nProcesses Information\n\nID\tBurst Time\tArrival Time\tPriority\n");
		for(int i=0; i< readyQueue.length; i++) {
			System.out.println(readyQueue[i].PID + "\t" + readyQueue[i].getBurstTime()+ "\t\t"
					 + readyQueue[i].getArrivalTime() + "\t\t" + readyQueue[i].getPriority());
		}//end for
		*/
		System.out.println("******************************************************");
		System.out.println("Process ID|\tCompletion Time|\tWait Time|\t"
				+ "Response Time|\tTurn Around Time|\t Arrival Time");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < readyQueue.length; i++) {
			System.out.println(processAnalysis[i][0] + "\t\t" + processAnalysis[i][1] + "\t\t\t"
					+ processAnalysis[i][2] + "\t\t" + processAnalysis[i][3] + "\t\t"
					 + processAnalysis[i][4] + "\t\t\t" + readyQueue[i].arrivalTime);
		} // end for
	}
	
	public float getAvgWaitTime() {
		float avgWaitTime = 0;
		if(!start) {
			scheduler();
		}
		for(int i=0; i < processCount; i++) {
			avgWaitTime += (processAnalysis[i][2])*1.0;
		}
		return (avgWaitTime/processCount);
	} //end getAvgWaitTime
	
	public float getAvgResponseTime() {
		float avgResponseTime = 0;
		if(!start) {
			scheduler();
		}
		for(int i=0; i < processCount; i++) {
			avgResponseTime += (processAnalysis[i][3])*1.0;
		}
		return (avgResponseTime/processCount);
	} //end getAvgResponseTime
	
	public float getAvgTurnAroundTime() {
		float avgTurnAroundTime = 0;
		if(!start) {
			scheduler();
		}
		for(int i=0; i < processCount; i++) {
			avgTurnAroundTime += (processAnalysis[i][4])*1.0;
		}
		return (avgTurnAroundTime/processCount);
	} //end getAvgTurnAroundTime
	
}// end class
