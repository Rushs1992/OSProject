package FinalProject;

public class FIFO {
	
	boolean schedulerRun;
	int totalProcesses; //process count
	int processesScheduled; //scheduled process count
	int currentTime; //current running time
	float averageWait;
	float totalTurnAroundTime;
	int averageResponse;
	Process[] readyQueue; // copy of ready queue
	int[][] runningQueue; // running queue
	
	FIFO(){
		currentTime = 0;
		totalTurnAroundTime = 0;
		averageWait = 0;
		averageResponse = 0;
		totalProcesses = 0;
		processesScheduled = 0;
		schedulerRun = false;
	}
	
	void scheduleProcesses(SimulateProcesses S) {
		totalProcesses = S.getProcessCount(); //get total processes
		runningQueue = new int[totalProcesses][6]; // initialize running queue
		readyQueue = new Process[totalProcesses]; // initialize ready queue
		for(int i=0; i<totalProcesses; i++) {
			readyQueue[i] = S.processQueue[i]; // copy running queue
			// set default values to running queue
			runningQueue[i][0] = readyQueue[i].getPID(); // PID
			runningQueue[i][1] = readyQueue[i].getBurstTime(); // Burst Time
			runningQueue[i][2] = readyQueue[i].getArrivalTime(); // Arrival Time
			runningQueue[i][3] = -1; // Wait Time
			runningQueue[i][4] = -1; // Turn Around Time
		} // end for
		int i  = 0;
		while(i < totalProcesses) {
			runningQueue[i][3] = currentTime - readyQueue[i].getArrivalTime();
			averageWait += runningQueue[i][3];
			currentTime += readyQueue[i].getBurstTime();
			runningQueue[i][4] = currentTime - readyQueue[i].getArrivalTime();
			totalTurnAroundTime += runningQueue[i][4];
			i++;
		}
		if(i == totalProcesses) {
			schedulerRun = true;
		}
	} //end scheduleProcesses
	
	public void printResult() {
		//method to print results
		System.out.println("FIFO scheduling results");
		System.out.println("******************************************************");
		System.out.println("Process ID|\tBurst Time|\tArrival Time|\tWait Time|\tTurn Around Time|");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < totalProcesses; i++) {
			System.out.println(runningQueue[i][0] + "\t\t" + runningQueue[i][1] + "\t\t\t"
					+ runningQueue[i][2] + "\t\t" + runningQueue[i][3] + "\t\t"
					 + runningQueue[i][4]);
		} // end for
		System.out.println("******************************************************");
		System.out.println("Average wait time:\t\t" + averageWait/totalProcesses);
		System.out.println("Average turn around time time:\t" + totalTurnAroundTime/totalProcesses);
		System.out.println("******************************************************");
	} //end printResult
	
	public float getAvgWaitTime() {
		if(schedulerRun) {
			return averageWait/totalProcesses;
		}else {
			System.out.println("\n Please run FIFO shceduler first");
		}
		return (float) (averageWait);
	} //end getAvgWaitTime
	
	public float getResponseTime() {
		return getAvgWaitTime();
	} // end getResponseTime
	
	public float getAvgTurnAroundTime() {
		if(schedulerRun) {
			return (totalTurnAroundTime/totalProcesses);
		}else {
			System.out.println("\n Please run priority shceduler first");
		}
		return 0;
	} //end getAvgTurnAroundTime
} //end class

