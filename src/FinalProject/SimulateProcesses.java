package FinalProject;

public class SimulateProcesses {
	
	int processCount;
	int counter = 0;
	Process[] processQueue;
	
	SimulateProcesses(int number){
		
		processCount = number;
		processQueue = new Process[processCount];
		
	} //end constructor
	
	public void addProcesses(Process P) {
		
		if(counter < processCount) {
			processQueue[counter] = P;
			counter++;
		} //end if
		
	} //end addProcesses
	
	public void printProcess() {
		
		System.out.println("\nProcesses Information\nID\tBurst Time\n");
		for(int i=0; i< processCount; i++) {
			System.out.println(processQueue[i].PID + "\t" + 
					processQueue[i].burstTime);
		}//end for
		
	} //end printProcess

} //end class
