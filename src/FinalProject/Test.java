package FinalProject;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Rushang created the test file");
		SimulateProcesses S = new SimulateProcesses(10);
		initializeProcess(S);
		S.printProcess();
		
		//Juan make out put
		
		//Meet make out put
		
		System.out.println("\nMeet");
		ShortestJobFirst T1 = new ShortestJobFirst();
		T1.Test_SPF(S);
		T1.DoShortestJobFirst(S);
	}
	
	static void initializeProcess(SimulateProcesses S) {
		for(int i=1; i<11; i++) {
			Process P = new Process(i);
			S.addProcesses(P);
		} //end for
		
	}// end initializeProcess

} //end class
