package FinalProject;
import java.util.Arrays;

public class ShortestJobFirst {

	float averageTAT;			// stores the average Turn Around Time of the Algorithm
	float averageWT;			// stores the average Wait time of the Algorithm
	float totalTAT = 0;			// to store the sum of turn around times of all the processes
	float totalWT = 0;			// to store the sum of wait times of all the processes 
	int index;					// to track the index of process in the ProcessQueue identified to be scheduled next
	int currentTime = 0;		// to keep track of current time of the scheduler
	int completedProcesses = 0;	// to keep count of processes that have been scheduled
	int[] processIDs;			// to store the IDs of each process
	int[] processTAT;			// result array to store the turn around time of each process
	int[] processWT;			// result array to store the wait time of each process
	int[] processCompTime;		// result array to store the completion time of each process
	int[] arrCompletedProcesses;// array used as a flag for scheduled processes
	
	ShortestJobFirst(SimulateProcesses S){
		// Constructor to initialize the result arrays
		processIDs = new int[S.processQueue.length];
		processTAT = new int[S.processQueue.length];
		processWT = new int[S.processQueue.length];
		processCompTime = new int[S.processQueue.length];
		arrCompletedProcesses = new int[S.processQueue.length];
		initializeArrays();	// Function to initialize the arrays to 0 or -1
	}
	
	
	public void DoShortestJobFirst(SimulateProcesses S) {
		// Function to implement the SJF scheduling algorithm
		while(completedProcesses < S.processCount) {
			// loop till all the processes have been scheduled
			int minimumProcessID = -1;		// to get the process ID having minimum burst time
			int minimumBurstTime = Integer.MAX_VALUE;	// initializing the minimum burst time with maximum value
			for(int i=0;i<S.processCount;i++) {
				// loop till you find the process having minimum burst time in the available processes
				if(S.processQueue[i].getArrivalTime() <= currentTime ) {
					if(S.processQueue[i].getBurstTime() < minimumBurstTime && arrCompletedProcesses[i] == 0) {
						minimumBurstTime = S.processQueue[i].getBurstTime();
						minimumProcessID = S.processQueue[i].getPID();
						index = i;	// to track the index of the process in ProcessQueue
					}
				}
				else {
					break;
				}
			}
			
			// Keeping the order of processes in result arrays similar to ProcessQueue
			processIDs[index] = minimumProcessID;
			processWT[index] = currentTime - S.processQueue[index].getArrivalTime();
			processCompTime[index] = currentTime + S.processQueue[index].getBurstTime();
			processTAT[index] = processCompTime[index] - S.processQueue[index].getArrivalTime();
	
			currentTime = processCompTime[index];	// Incrementing the currentTime with the completion time of the scheduled process
			arrCompletedProcesses[index] +=1;	// Flagging the process that is scheduled
			completedProcesses +=1;		// Incrementing counter of scheduled processes
			
			totalTAT += (float) processTAT[index];	// adding the Turn Around Time of the scheduled process to sum
			totalWT += (float) processWT[index];	// adding the Wait Time of the scheduled process to sum
		}
		
		averageTAT = totalTAT/S.processQueue.length;	// calculating average of Turn Around Time
		averageWT = totalWT/S.processQueue.length;		// calculating average of Wait Time
		
//		printResults(S);	//Print the Results
	
	}

	private void initializeArrays() {
		//This function initializes result arrays to 0 or -1
		Arrays.fill(processIDs, -1);
		Arrays.fill(processTAT, -1);
		Arrays.fill(processWT, -1);
		Arrays.fill(processCompTime, -1);
		Arrays.fill(arrCompletedProcesses, 0);
	}
	
	private void printResults(SimulateProcesses S) {
		// This function prints the arrays in tabular form and prints the average TAT, WT and RT
		System.out.println("ID\tBurst Time\tArrival Time\tResponse Time\tComp Time\tTAT\tWT");
		for(int i=0;i<S.processQueue.length;i++) {
			System.out.println(processIDs[i]+"\t"+S.processQueue[i].getBurstTime()+"\t\t"+S.processQueue[i].getArrivalTime()+"\t\t"
					+processWT[i]+"\t\t"+processCompTime[i]+"\t\t"+processTAT[i]+"\t"+processWT[i]);
		}
		System.out.println("The average Turn Around Time is " + averageTAT);
		System.out.println("The average Waiting Time is " + averageWT);
		System.out.println("The average Response Time is " + averageWT);	// Waiting Time = Response Time in SJF
		System.out.println("Completion Time: " + currentTime);	// Completion time = time at which the last process ends
	}
	
	public float getAvgTurnAround() {
		// Public method to get the average Turn Around Time
		return averageTAT;
	}
	
	public float getAvgResp() {
		// Public method to get the average Response Time
		return averageWT;	// Response Time = Waiting Time in SJF
	}
	
	public float getAvgWait() {
		// Public method to get the average Wait Time
		return averageWT;
	}
	
	public int getComptTime() {
		// Public method to get the Completion Time
		return currentTime;		// Completion time = time at which the last process ends
	}
	
}
