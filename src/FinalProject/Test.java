package FinalProject;

import java.util.Random;

public class Test {
		
	static int processes_count = 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println("\nRushang");
		SimulateProcesses S = new SimulateProcesses(processes_count);
		initializeProcess(S);
		S.printProcess();

		Priority obj = new Priority();
		obj.scheduleProcesses(S);
		System.out.println("\nRushang finish");

		
		System.out.println("\nJuan\n");
		System.out.println("\nRound Robin Execution\n");
		RoundRobin R1 = new RoundRobin();
		int[] completed_time = R1.getCompTime(S, 3);
		R1.print_results(completed_time, S);
		System.out.println("AVG RT: "+R1.get_RT(S));
		

		
//		Priority obj = new Priority();
//		obj.scheduleProcesses(S);
//		System.out.println("\nPriority Scheduling Output");
//		System.out.println("TAT: "+obj.getAvgTurnAroundTime());
//		System.out.println("WT: "+obj.getAvgWaitTime());
//		System.out.println("RT: "+obj.getResponseTime());
		

		
//		System.out.println("\nRushang finish");
		
		
//		System.out.println("\nJuan\n");
//		System.out.println("\nRound Robin Execution\n");
//		RoundRobin R1 = new RoundRobin();
//		int[] completed_time = R1.getCompTime(S, 3);
//		R1.get_RT(S);
//		R1.print_results(completed_time, S);
//		System.out.println("\nJuan finish");
		
		
//		System.out.println("\nMeet\n");	
//		ShortestJobFirst T1 = new ShortestJobFirst(S);
//		T1.DoShortestJobFirst(S);
//		System.out.println("\nShortest Job First");
//		System.out.println("TAT: " + T1.getAvgTurnAround());
//		System.out.println("WT: " + T1.getAvgWait());
//		System.out.println("RT: " + T1.getAvgResp());
		
//		System.out.println("\nMeet finish\n");
	
    
//		**Current Wait Time Algorithm
		

	
		
		CurrentWaitTime C01 = new CurrentWaitTime(S,0.1);
		C01.scheduler();
//		C.printAnalysis();
		System.out.println("\nCurrent Wait Time for mf 0.1");
		System.out.println("TAT: "+C01.getAvgTurnAroundTime());
		System.out.println("WT: "+C01.getAvgWaitTime());
		System.out.println("RT: "+C01.getAvgResponseTime());
		
		CurrentWaitTime C02 = new CurrentWaitTime(S,0.2);
		C02.scheduler();
//		C.printAnalysis();
		System.out.println("\nCurrent Wait Time for mf 0.2");
		System.out.println("TAT: "+C02.getAvgTurnAroundTime());
		System.out.println("WT: "+C02.getAvgWaitTime());
		System.out.println("RT: "+C02.getAvgResponseTime());
		
		CurrentWaitTime C03 = new CurrentWaitTime(S,0.3);
		C03.scheduler();
//		C.printAnalysis();
		System.out.println("\nCurrent Wait Time for mf 0.3");
		System.out.println("TAT: "+C03.getAvgTurnAroundTime());
		System.out.println("WT: "+C03.getAvgWaitTime());
		System.out.println("RT: "+C03.getAvgResponseTime());
		
		CurrentWaitTime C04 = new CurrentWaitTime(S,0.4);
		C04.scheduler();
//		C.printAnalysis();
		System.out.println("\nCurrent Wait Time for mf 0.4");
		System.out.println("TAT: "+C04.getAvgTurnAroundTime());
		System.out.println("WT: "+C04.getAvgWaitTime());
		System.out.println("RT: "+C04.getAvgResponseTime());
		
		CurrentWaitTime C05 = new CurrentWaitTime(S,0.5);
		C05.scheduler();
//		C.printAnalysis();
		System.out.println("\nCurrent Wait Time for mf 0.5");
		System.out.println("TAT: "+C05.getAvgTurnAroundTime());
		System.out.println("WT: "+C05.getAvgWaitTime());
		System.out.println("RT: "+C05.getAvgResponseTime());
		
//		FIFO obj1 = new FIFO();
//		obj1.scheduleProcesses(S);
//		System.out.println("\nFIFO");
//		System.out.println("TAT: "+obj1.getAvgTurnAroundTime());
//		System.out.println("WT :"+obj1.getAvgWaitTime());
//		System.out.println("RT: "+obj1.getResponseTime());

		
	} //end psvm

	
static void initializeProcess(SimulateProcesses S) {
		for(int i=1; i<=processes_count; i++) {
			Process P = new Process(i);
			S.addProcesses(P);
		} //end for
		
	}// end initializeProcess


} //end class
