package FinalProject;

public class ProcessNewProperties {
	
	int PID;
	int remaingBurstTime;
	int arrivalTime;
	int priority;
	int readyQueueIndex;
	float waitTime;
	boolean completed;
	boolean setResponseTime;
	boolean firstWaitTime;

	ProcessNewProperties(Process P, int index){
		
		PID = P.getPID();
		remaingBurstTime = P.getBurstTime();
		arrivalTime = P.getArrivalTime();
		priority = P.getPriority();
		waitTime = 0;
		readyQueueIndex = index;
		completed = false;
		setResponseTime = true;
		firstWaitTime = true;
	} // end constructor
}
