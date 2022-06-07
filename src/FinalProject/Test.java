package FinalProject;

import java.util.Random;

public class Test {
		
	static int processes_count = 5;
	static double mFactor = 0.1;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulateProcesses S = new SimulateProcesses(processes_count);
		initializeProcess(S);
		S.printProcess();

		


//		**FIFO Algorithm
		
		FIFO obj1 = new FIFO();
		obj1.scheduleProcesses(S);
		System.out.println("\nFIFO");
		obj1.printResult();
		System.out.println("TAT: "+obj1.getAvgTurnAroundTime());
		System.out.println("WT :"+obj1.getAvgWaitTime());
		System.out.println("RT: "+obj1.getResponseTime());
		
		

//		**Priority Algorithm
		
		Priority obj = new Priority();
		obj.scheduleProcesses(S);
		System.out.println("\nPriority Scheduling Output");
		obj.printResult();
		System.out.println("TAT: "+obj.getAvgTurnAroundTime());
		System.out.println("WT: "+obj.getAvgWaitTime());
		System.out.println("RT: "+obj.getResponseTime());

		

		System.out.println("\nJuan\n");
		System.out.println("\nRound Robin Execution\n");
		RoundRobin R1 = new RoundRobin();
		int[] completed_time = R1.getCompTime(S, 3);
		R1.print_results(completed_time, S);
		System.out.println("AVG RT: "+R1.get_RT(S));

		
//		**Round Robin Algorithm (in process for report)
//		System.out.println("\nJuan\n");
//		System.out.println("\nRound Robin Execution\n");
//		RoundRobin R1 = new RoundRobin();
//		int[] completed_time = R1.getCompTime(S, 3);
//		R1.get_RT(S);
//		R1.print_results(completed_time, S);
//		System.out.println("\nJuan finish");
    
		
    
//		**Shortest Job First Algorithms.
		
		ShortestJobFirst T1 = new ShortestJobFirst(S);
		T1.DoShortestJobFirst(S);
		System.out.println("\nShortest Job First");
		T1.printResults(S);
		System.out.println("TAT: " + T1.getAvgTurnAround());
		System.out.println("WT: " + T1.getAvgWait());
		System.out.println("RT: " + T1.getAvgResp());	
    

    
//		**Current Wait Time Algorithm
		
		CurrentWaitTime C01 = new CurrentWaitTime(S,mFactor);
		C01.scheduler();
		System.out.println("\nCurrent Wait Time for mf " + mFactor);
		C01.printResults();
		System.out.println("TAT: "+C01.getAvgTurnAroundTime());
		System.out.println("WT: "+C01.getAvgWaitTime());
		System.out.println("RT: "+C01.getAvgResponseTime());
		
	} //end psvm

	
static void initializeProcess(SimulateProcesses S) {
		for(int i=1; i<=processes_count; i++) {
			Process P = new Process(i);
			S.addProcesses(P);
		} //end for
		
	}// end initializeProcess


} //end class
