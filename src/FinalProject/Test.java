package FinalProject;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Rushang created the test file");
		SimulateProcesses S = new SimulateProcesses(10);
		initializeProcess(S,10);
		S.printProcess();
		Priority obj = new Priority();
		obj.scheduleProcesses(S);

		// Methods added
		System.out.println("\n Round Rabin Algorithm");
		RoundRobin R1 = new RoundRobin();
		int[] completed_time = R1.getCompTime(S, 3);
		R1.print_results(completed_time, S);
		//Metrics
		System.out.println(R1.get_TAT(completed_time, S));
		System.out.println(R1.get_WT(completed_time, S));
		System.out.println(R1.get_RT());
		System.out.println(R1.get_CT(completed_time));
//		
//		//Meet make out put
//		
		System.out.println("\n Shortest Job First Algorithm");
		ShortestJobFirst T1 = new ShortestJobFirst();
		T1.Test_SPF(S);
		T1.DoShortestJobFirst(S);

		// Current Wait Time Algorithm
		
		CurrentWaitTime C = new CurrentWaitTime(S);
		C.scheduler();
		C.printAnalysis();
		
	} //end psvm

	
static void initializeProcess(SimulateProcesses S,int amount_processes) {
		for(int i=1; i<=amount_processes; i++) {
			Process P = new Process(i);
			S.addProcesses(P);
		} //end for
		
	}// end initializeProcess


} //end class
