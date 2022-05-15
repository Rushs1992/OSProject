package FinalProject;

public class ShortestJobFirst {

	int averageTAT;
	int averageWT;
	int averageRT;
	int totalTAT = 0;
	int totalWT = 0;
	
	int currentTime = 0;
	int completedProcesses = 0;
	int previousTime;
	
	
	public void DoShortestJobFirst(SimulateProcesses S) {
		System.out.println("**Beta Test. Sector DoShortestJobFirst infiltrated successfully\n\n");
		int i;
		int[] arrCompletedProcesses = new int[S.processQueue.length];
		while(completedProcesses < S.processQueue.length) {
			int minimumProcessID=-1;
			int minimumBurstTime = Integer.MAX_VALUE;
			for(i=0;i<S.processQueue.length;i++) {
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
//			if(minimumProcessID != -1) {
				totalTAT += S.processQueue[minimumProcessID]
//			}
//			else {
//				currentTime++;
//			}
		}
	}
	
	
	
	
	
	public void Test_SPF(SimulateProcesses S){
		
		System.out.println("**Aplha Test. Sector Test_SPF infiltrated successfully\n"
				+ "Extracting Burst time of process 2 in queue: " 
				+S.processQueue[2].getBurstTime()
				+"\n\n");
	}
}
