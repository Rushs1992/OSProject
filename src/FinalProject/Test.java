package FinalProject;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\nRushang");
		SimulateProcesses S = new SimulateProcesses(10);
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
		System.out.println("\nJuan finish");

		
		
		//Meet make out put
		
		System.out.println("\nMeet");
		ShortestJobFirst T1 = new ShortestJobFirst();
//		T1.Test_SPF(S);
		T1.DoShortestJobFirst(S);
		System.out.println("\nMeet finish\n");


		// Current Wait Time Algorithm
		
		CurrentWaitTime C = new CurrentWaitTime(S);
		C.scheduler();
		C.printAnalysis();
		
	} //end psvm

	
static void initializeProcess(SimulateProcesses S) {
		for(int i=1; i<=10; i++) {
			Process P = new Process(i);
			S.addProcesses(P);
		} //end for
		
	}// end initializeProcess


} //end class
