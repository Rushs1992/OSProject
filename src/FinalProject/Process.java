package FinalProject;

public class Process {

	int PID;
	int burstTime;
	int arrivalTime;
	int priority;
	
	Process(int ID){
		PID = ID;
	} // end constructor
	
	public int getPID() {
		return PID;
	}
	
	public void setBurstTime(int bTime) {
		burstTime = bTime;
	}// end setBurstTime
	
	public int getBurstTime() {
		return burstTime;
	} //end getBurstTime
	
	public void setArrivalTime(int aTime) {
		arrivalTime = aTime;
	}// end setArrivalTime
	
	public int getArrivalTime() {
		return arrivalTime;
	} //end getArrivalTime
	
	public void setPriority(int pLevel) {
		arrivalTime = pLevel;
	}// end setPriority
	
	public int getPriority() {
		return priority;
	} //end getPriority
} //end class
