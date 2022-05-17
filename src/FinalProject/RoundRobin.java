package FinalProject;

public class RoundRobin {
	  int[] response_time;
	  int[] turn_around;
	  int[] waiting_time;
	  
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
		  
		  
		  int in_queue = S.processQueue.length-1;
		  
		  for(int i = 0; i < S.processQueue.length; i++) {
			 arrival_time[S.processQueue[i].getPID()-1] =  S.processQueue[i].arrivalTime;
			 first_contact[S.processQueue[i].getPID()-1] = false;
			 rem_time[S.processQueue[i].getPID()-1] = S.processQueue[i].burstTime;
			 queue[i] = S.processQueue[i];
		  }
		  
		  while(completed != queue.length) {
			  //Processes are put in the ready queue as they arrive
			  if(in_cpu == null) {
				  in_cpu = queue[0];
				  id = queue[0].PID-1;
				  queue[0] = null;
				  count = true;
				  if(first_contact[id] == false) {
					  response_time[id] = time;
					  first_contact[id] = true;
				  }
			  }
			  
			  if(rem_time[id] == 0) {
				  completed++;
				  completed_time[id] = time;
				  in_cpu = queue[0];
				  count = false;
				  quantum = 3;
				  for(int i = 0; i < in_queue;i++) {
					  queue[i] = queue[i+1];
					  queue[i+1] = null;
					  
				  }
				  in_queue--;
				  
			  }
			  
			  if(quantum == 0) {
				  tmp = in_cpu;
				  in_cpu = queue[0];
				  count = false;
				  quantum = 3;
				  for(int i = 0; i < in_queue;i++) {
					  queue[i] = queue[i+1];
					  queue[i+1] = null;
					  
				  }
				  queue[in_queue] = tmp;
				  
			  }
			  
			

			if(count == true) {
			  rem_time[id]--;
			  quantum--;
			  time++;
			 }
		  }
		  
		  return completed_time;
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
