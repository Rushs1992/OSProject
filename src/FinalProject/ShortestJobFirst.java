package FinalProject;

public class ShortestJobFirst {

	int averageTAT;
	int averageWT;
	int averageRT;
	int totalTAT = 0;
	int totalWT = 0;
	int totalRT = 0;
	int currentTime = 0;
	
	int currentCompletionTime = 0;
	int currentTAT = 0;
	
	int completedProcesses = 0;
	int previousTime;
	
	
	public void DoShortestJobFirst(SimulateProcesses S) {
		System.out.println("**Beta Test. Sector DoShortestJobFirst infiltrated successfully\n\n");
		int i;
		int[] arrCompletedProcesses = new int[S.processQueue.length];
		while(completedProcesses < S.processCount) {
			System.out.println("In the while loop");
			int minimumProcessID = -1;
			int minimumBurstTime = Integer.MAX_VALUE;
			for(i=0;i<S.processCount;i++) {
				System.out.println("In the for loop");
				if(S.processQueue[i].getArrivalTime() <= currentTime && arrCompletedProcesses[i] == 0) {
					if(S.processQueue[i].getBurstTime() < minimumBurstTime) {
						minimumBurstTime = S.processQueue[i].getBurstTime();
						minimumProcessID = S.processQueue[i].getPID();
					}
//					if(S.processQueue[i].getBurstTime() < minimumBurstTime) {
//						if(S.processQueue[i].getArrivalTime() < S.processQueue[minimumProcessID].getArrivalTime()) {
//							minimumBurstTime = S.processQueue[i].getBurstTime();
//							minimumProcessID = S.processQueue[i].getPID();
//						}
//					}
				}
				else {
					break;
				}
			}
			totalRT += (currentTime - S.processQueue[minimumProcessID].getArrivalTime());
			currentCompletionTime = currentTime + S.processQueue[minimumProcessID].getBurstTime();
			currentTAT = (currentCompletionTime - S.processQueue[minimumProcessID].arrivalTime);
			totalTAT += currentTAT;
			totalWT += currentTAT - S.processQueue[minimumProcessID].getBurstTime();
			System.out.println("Completed Process Count: " + completedProcesses );
			arrCompletedProcesses[minimumProcessID] +=1;
			completedProcesses +=1;
			currentTime = currentCompletionTime;
//			if(minimumProcessID != -1) {
//				totalTAT += S.processQueue[minimumProcessID]
//			}
//			else {
//				currentTime++;
//			}
		}
		System.out.println("\nThe Total TAT is " + totalTAT);
		System.out.println("\nThe Total WT is " + totalWT);
		averageTAT = totalTAT/S.processQueue.length;
		averageWT = totalWT/S.processQueue.length;
		System.out.println("The average TAT is " + averageTAT);
		System.out.println("The average WT is " + averageWT);
	
	}
	
	
	
	
	
	public void Test_SPF(SimulateProcesses S){
		
		System.out.println("**Aplha Test. Sector Test_SPF infiltrated successfully\n"
				+ "Extracting Burst time of process 2 in queue: " 
				+S.processQueue[2].getBurstTime()
				+"\n\n");
	}
}
