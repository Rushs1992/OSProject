package FinalProject;

import java.util.HashMap;

public class Priority {
	
	int totalProcesses; //process count
	int processesScheduled; //scheduled process count
	int currentTime; //current running time
	float totalCompletion;
	float averageWait;
	int averageResponse;
	int currentPriority;
	Process[] readyQueue; // copy of ready queue
	int[][] runningQueue; // running queue
	HashMap<Integer,Integer> completedProcesses; // hashmap to keep track of completed processes
	
	Priority(){ // constructor
		currentTime = 0;
		totalCompletion = 0;
		averageWait = 0;
		averageResponse = 0;
		totalProcesses = 0;
		processesScheduled = 0;
		completedProcesses = new HashMap<Integer,Integer>();
	} // end constructor
	
	public void scheduleProcesses(SimulateProcesses S) {
		totalProcesses = S.getProcessCount(); //get total processes
		runningQueue = new int[totalProcesses][6]; // initialize running queue
		readyQueue = new Process[totalProcesses]; // initialize ready queue
		for(int i=0; i<totalProcesses; i++) {
			readyQueue[i] = S.processQueue[i]; // copy running queue
			// set default values to running queue
			runningQueue[i][0] = -1; // PID
			runningQueue[i][1] = -1; // Priority
			runningQueue[i][2] = -1; // Arrival Time
			runningQueue[i][3] = -1; //Burst Time
			runningQueue[i][4] = -1; // Completion Time
			runningQueue[i][5] = -1; // Wait Time
		} // end for
		
		while(processesScheduled < totalProcesses) {
			// loop to schedule all the process in the ready queue
			int index = findNextProcess(); // search the next process to be scheduled
			completedProcesses.put(readyQueue[index].getPID(), 1); // add completed process to the hash map
			addProcessDetails(index, processesScheduled); // add process details to the running queue
			runningQueue[processesScheduled][5] = currentTime - readyQueue[index].getArrivalTime(); //add wait time for the process
			averageWait += currentTime; // add wait time to calculate average wait time
			currentTime += readyQueue[index].getBurstTime(); //increase current time
			totalCompletion += currentTime; // add total completion time
			runningQueue[processesScheduled][4] = currentTime; //add process completion time
			processesScheduled++; //increase scheduled process count
		}
		printResult();
		
	} // end scheduleProcesses
	
	private int findNextProcess() {
		// finds index from the ready queue for the next process
		int swapIndex = -1;
		int tempIndex = -1;
		currentPriority = -1;
		for(int j = 0; j < totalProcesses; j++) {
			//compares if the arrival time is less than current time
			if(readyQueue[j].getArrivalTime() <= currentTime) {
				// checks whether the process is already completed or not
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
				tempIndex = j;
				break;
			}
		} //end for
		if(swapIndex < 0) {
			//if current time is less than the arrival time of next processs then
			// get the first element from the ready queue
			System.out.println("\n\t****** Error ********");
			swapIndex = tempIndex;
		}
		return swapIndex;
	} // end findNextProcess

	public void printResult() {
		//method to print results
		System.out.println("Priority scheduling results");
		System.out.println("******************************************************");
		System.out.println("Process ID|\tPriority|\tArrival Time|\tBurst Time|\t"
				+ "Completion Time|\tWait Time|");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < totalProcesses; i++) {
			int index = findIndex(readyQueue[i].getPID());
			System.out.println(runningQueue[index][0] + "\t\t" + runningQueue[index][1] + "\t\t\t"
					+ runningQueue[index][2] + "\t\t" + runningQueue[index][3] + "\t\t"
					 + runningQueue[index][4] + "\t\t" + runningQueue[index][5]);
		} // end for
		System.out.println("******************************************************");
		System.out.println("Average wait time:\t\t" + averageWait*1.0/totalProcesses);
		System.out.println("Average completion time:\t" + totalCompletion*1.0/totalProcesses);
		System.out.println("******************************************************");
	} //end printResult

	public void addProcessDetails(int index, int queueIndex) {
		// add process details to running queue
		runningQueue[queueIndex][0] = readyQueue[index].getPID();
		runningQueue[queueIndex][1] = readyQueue[index].getPriority();
		runningQueue[queueIndex][2] = readyQueue[index].getArrivalTime();
		runningQueue[queueIndex][3] = readyQueue[index].getBurstTime();
	} // end addProcessDetails
	
	private int findIndex(int ID) {
		// finds the next process ID
		for(int i = 0; i<totalProcesses;i++) {
			if(runningQueue[i][0] == ID) {
				return i;
			} //end if
		} // end for
		return -1;
	} //end findIndex
	
} // end class
