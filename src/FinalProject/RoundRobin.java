package FinalProject;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
	  int[] response_time;
	  int[] turn_around;
	  int[] waiting_time;
	  Queue<Process> ready_queue = new LinkedList<Process>();
	  
	  public RoundRobin() {
		  
		  
	  }
	  
	  public int[] getCompTime(SimulateProcesses S,int quantum) {
		  int[] rem_time = new int[S.processQueue.length];
		  boolean[] first_contact = new boolean[S.processQueue.length];
		  int completed = 0;
		  int id = 0;
		  int time = 0;
		  boolean count = false;
		  response_time = new int[S.processQueue.length];
		  int[] completed_time = new int[S.processQueue.length];
		  Process[] queue = new Process[S.processQueue.length];
		  int[] arrival_time = new int[S.processQueue.length];
		  Process in_cpu = null;
		  Process tmp = null;
		  
		  
		  for(int i = 0; i < S.processQueue.length; i++) {
			 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
			 first_contact[S.processQueue[i].getPID()-1] = false;
			 rem_time[S.processQueue[i].getPID()-1] = S.processQueue[i].burstTime;
			 queue[i] = S.processQueue[i];
		  }
		  
		  while(completed != queue.length) {
			  //Processes are put in the ready queue as they arrive
			  // Put processes in ready queue as they arrive
			  for(int i = 0; i < queue.length;i++) {
				  if(queue[i].arrivalTime == time) {
					  if(ready_queue.size() != queue.length) {
						  ready_queue.add(queue[i]);
					  } 
				  }
			  }
			  // Schedule a new process
			  if(in_cpu == null) {
				  in_cpu = ready_queue.remove();  
				  count = true;
				  if(first_contact[in_cpu.PID-1] == false) {
					  response_time[in_cpu.PID-1] = time;
					  first_contact[in_cpu.PID-1] = true;
				  }
			  }
			  
		// Verify if the process has finished
			  
			  if(rem_time[in_cpu.PID-1] == 0) {
				  completed++;
				  completed_time[in_cpu.PID-1] = time;
				  in_cpu = null;
				  count = false; 
				  quantum = 3;  
			  }
			  // Verify if quantum its over
			  if(quantum == 0) {
				  tmp = in_cpu;
				  in_cpu = null;
				  count = false;
				  quantum = 3;
				  if(ready_queue.size() != queue.length) {
					  ready_queue.add(tmp);
				  }
				 
			  }
			  
			if(count == true) {
				  rem_time[in_cpu.PID-1]--;
				  quantum--;
				  time++;
			}
			
		
		  }
		  
		  return completed_time;
	  }
	  
	  
	  
	  
	  public float get_TAT(int[] completed_time, SimulateProcesses S) {
			int[] arrival_time = new int[S.processQueue.length];
		  	turn_around = new int[S.processQueue.length];
			Process[] queue = new Process[S.processQueue.length];
		  	float avg_turn_around = 0;
		  	
		  	 for(int i = 0; i < S.processQueue.length; i++) {
				 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
				 queue[i] = S.processQueue[i];
			  }
			
			  for(int i = 0; i < completed_time.length; i++){
					turn_around[i] = completed_time[i] - arrival_time[i];
			  }
			  
			  for(int i = 0; i < completed_time.length; i++){
					avg_turn_around = avg_turn_around + turn_around[i];
			}
		  
		  	avg_turn_around = avg_turn_around/turn_around.length;
		  	return avg_turn_around;
	  }
	  
	  public float get_WT(int[] completed_time, SimulateProcesses S) {
		  	int[] burst_time = new int[S.processQueue.length];
			int[] arrival_time = new int[S.processQueue.length];
			waiting_time = new int[S.processQueue.length];
			Process[] queue = new Process[S.processQueue.length];
			float avg_waiting = 0;
		  	
			 for(int i = 0; i < S.processQueue.length; i++) {
				 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
				 burst_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].burstTime;
				 queue[i] = S.processQueue[i];
			  }
			
			
			  for(int i = 0; i < completed_time.length; i++){
				  waiting_time[i] = (completed_time[i] - arrival_time[i]) - burst_time[i];
			  }
			  
			  for(int i = 0; i < completed_time.length; i++){
					avg_waiting = avg_waiting + waiting_time[i];
			}
		  
		  	avg_waiting = avg_waiting/waiting_time.length;
		  	return avg_waiting;
	  }
	  
	  public float get_RT(SimulateProcesses S) {
		  float avg_response_time = 0;
		  int[] arrival_time = new int[S.processQueue.length];
		  
		  for(int i = 0; i < S.processQueue.length; i++) {
				 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
			  }
			
		  
		  for(int i = 0; i < response_time.length; i++){
			  response_time[S.processQueue[i].getPID()-1] = response_time[S.processQueue[i].getPID()-1] - arrival_time[S.processQueue[i].getPID()-1] ;
		  }
		  
		  for(int i = 0; i < response_time.length; i++){
			  avg_response_time = avg_response_time + response_time[i];
		  }
		  
		  	return avg_response_time/response_time.length;
	  }
	  
	  public float get_CT(int[] completed_time) {
		  float avg_CT = 0;
		  for(int i = 0; i < completed_time.length; i++){
			  avg_CT = avg_CT + completed_time[i];
		  }
		  
		  	return avg_CT/completed_time.length;
	  }
	  
	  
	  
	  public void print_results(int[] completed_time, SimulateProcesses S) {
		  	int[] arrival_time = new int[S.processQueue.length];
		  	int[] burst_time = new int[S.processQueue.length];
		  	turn_around = new int[S.processQueue.length];
		  	waiting_time = new int[S.processQueue.length];
		  	Process[] queue = new Process[S.processQueue.length];
		  	float avg_turn_around = 0;
			float avg_waiting = 0;
			
			
			 for(int i = 0; i < S.processQueue.length; i++) {
				 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
				 burst_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].burstTime;
				 queue[i] = S.processQueue[i];
			  }
			
			// Calculating the turn around and waiting time for each process
			for(int i = 0; i < completed_time.length; i++){
				turn_around[i] = completed_time[i] - arrival_time[i];
				waiting_time[i] = turn_around[i] - burst_time[i];
			}
			
			
			// Printing the values for each of the processes
			System.out.println("Processes "+" Arrival Time "+" Burst Time "+"	Completion Time "+"Response Time	"+"Turn Around Time "+" Waiting Time ");
			
			for(int i = 0; i < completed_time.length; i++){
				System.out.println("P"+(i+1)+"\t\t"+arrival_time[i]+"\t\t "+burst_time[i]+"\t\t "+completed_time[i]+"\t\t "+response_time[i]+"\t\t "+turn_around[i]+"\t\t "+waiting_time[i]);
			}
			
			// We calculate the average turn around and waiting time
			for(int i = 0; i < completed_time.length; i++){
				avg_turn_around = avg_turn_around + turn_around[i];
				avg_waiting = avg_waiting + waiting_time[i];
			}
			
			// Printing those values
			System.out.println("Average Turn Around: "+avg_turn_around/turn_around.length+"\t\t"+"Average Waiting Time: "+avg_waiting/waiting_time.length);
		  
	  }

	

}
