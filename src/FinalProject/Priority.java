package FinalProject;

import java.util.HashMap;

public class Priority {
	
	int totalProcesses;
	int processesScheduled;
	int currentTime;
	int totalCompletion;
	int averageWait;
	int averageResponse;
	int currentPriority;
	Process[] readyQueue;
	int[][] runningQueue;
	HashMap<Integer,Integer> completedProcesses;
	
	Priority(){
		currentTime = 0;
		totalCompletion = 0;
		averageWait = 0;
		averageResponse = 0;
		totalProcesses = 0;
		processesScheduled = 0;
		completedProcesses = new HashMap<Integer,Integer>();
	} // end constructor
	
	public void scheduleProcesses(SimulateProcesses S) {
		totalProcesses = S.getProcessCount();
		runningQueue = new int[totalProcesses][6];
		readyQueue = new Process[totalProcesses];
		for(int i=0; i<totalProcesses; i++) {
			readyQueue[i] = S.processQueue[i];
			runningQueue[i][0] = -1; // PID
			runningQueue[i][1] = -1; // Priority
			runningQueue[i][2] = -1; // Arrival Time
			runningQueue[i][3] = -1; //Burst Time
			runningQueue[i][4] = -1; // Completion Time
			runningQueue[i][5] = -1; // Wait Time
		} // end for
		
		while(processesScheduled < totalProcesses) {
			int index = findNextProcess();
			completedProcesses.put(readyQueue[index].getPID(), 1);
			addProcessDetails(index, processesScheduled);
			runningQueue[processesScheduled][5] = currentTime;
			currentTime += readyQueue[index].getBurstTime();
			runningQueue[processesScheduled][4] = currentTime;
			processesScheduled++;
		}
		printResult();
		
	} // end scheduleProcesses
	
	private int findNextProcess() {
		int swapIndex = -1;
		currentPriority = -1;
		for(int j = 0; j < totalProcesses; j++) {
			if(readyQueue[j].getArrivalTime() <= currentTime) {
				if(!completedProcesses.containsKey(readyQueue[j].getPID())) {
					if(currentPriority == -1) {
						currentPriority = readyQueue[j].getPriority();
						swapIndex = j;
					}else if(readyQueue[j].getPriority() < currentPriority) {
						currentPriority = readyQueue[j].getPriority();
						swapIndex = j;						
					}
				}
			}else {
				break;
			}
		} //end for
		return swapIndex;
	} // end findNextProcess

	public void printResult() {
		
		System.out.println("Priority scheduling results");
		System.out.println("******************************************************");
		System.out.println("Process ID|\tPriority|\tArrival Time|\tBurst Time|\t"
				+ "Completion Time|\tWait Time|");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < totalProcesses; i++) {
			System.out.println(runningQueue[i][0] + "\t\t" + runningQueue[i][1] + "\t\t\t"
					+ runningQueue[i][2] + "\t\t" + runningQueue[i][3] + "\t\t"
					 + runningQueue[i][4] + "\t\t" + runningQueue[i][5]);
		} // end for
	} //end printResult

	public void addProcessDetails(int index, int queueIndex) {
		runningQueue[queueIndex][0] = readyQueue[index].getPID();
		runningQueue[queueIndex][1] = readyQueue[index].getPriority();
		runningQueue[queueIndex][2] = readyQueue[index].getArrivalTime();
		runningQueue[queueIndex][3] = readyQueue[index].getBurstTime();
	} // end addProcessDetails
	
} // end class
