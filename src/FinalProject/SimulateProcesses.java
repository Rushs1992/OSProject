package FinalProject;

import java.util.Random;

public class SimulateProcesses {
	
	int processCount;
	int pQcounter = 0;
	int totalBurstTime = 0;
	Process[] processQueue;
	Process[] readyQueue;
	
	SimulateProcesses(int number){
		
		processCount = number;
		processQueue = new Process[processCount];
		readyQueue = new Process[processCount];
		
	} //end constructor
	
	public void addProcesses(Process P) {
		
		if(pQcounter < processCount) {
			processQueue[pQcounter] = P;
			totalBurstTime += processQueue[pQcounter].getBurstTime();
			addArrivalTime(pQcounter);
			pQcounter++;
		} //end if
		
	} //end addProcesses
	
	public void printProcess() {
		
		System.out.println("\nProcesses Information\n\nID\tBurst Time\tArrival Time\n");
		for(int i=0; i< processCount; i++) {
			System.out.println(processQueue[i].PID + "\t" + 
					processQueue[i].burstTime + "\t\t" + processQueue[i].arrivalTime);
		}//end for
		System.out.println("Total burst time:  " + totalBurstTime);
	} //end printProcess
	
	public void addArrivalTime(int index) {
		readyQueue[index] = processQueue[index];
		if(index == 0) {
			readyQueue[index].setArrivalTime(0);
		}else {
			Random rn = new Random();
			int time = (1+ rn.nextInt(totalBurstTime/3));
			//time = (int) (time%(0.8*totalBurstTime));
			readyQueue[index].setArrivalTime(time);
		} //end if-else
	} // end addArrivalTime

} //end class
