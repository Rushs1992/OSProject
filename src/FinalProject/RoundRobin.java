package FinalProject;

public class RoundRobin {
	  int pid;
	  int arrival_time;
	  int burst_cpu;
	  int remaining_time;
	  int completed_time;
	  int response_time;
	  int turn_around;
	  int waiting_time;
	  int quantum;
	  
	  public RoundRobin() {
		  
		  
	  }
	  
	  public RoundRobin(int pid,int arrival_time,int burst_time) {
		  this.pid = pid;
		  this.arrival_time = arrival_time;
		  this.burst_cpu = burst_time;
	  }
	  
	  public void getCompTime(RoundRobin processes[],int quantum) {
		  int completed = 0;
		  int time = 0;
		  int id = 0;
		  int in_queue = 0;
		  boolean cpu_busy = false;
		  RoundRobin[] ready_queue = new RoundRobin[processes.length];
		  RoundRobin in_cpu = new RoundRobin();
		  
		  for(int i = 0; i < processes.length; i++) {
			  processes[i].remaining_time = processes[i].burst_cpu;
			  processes[i].quantum = quantum;
		  }
		  
		  while(completed != processes.length) {
			  //Processes are put in the ready queue as they arrive
			  for(int i = 0; i < processes.length; i++) {
				  if(processes[i].arrival_time == time) {
					  ready_queue[in_queue] = processes[i];
					  in_queue++;
				  }
			  }
			  
			  
			  // If a process is done with its execution, its completed time is saved and replaced
			  if(processes[id].remaining_time == 0) {
				  processes[id].completed_time = time;
				  completed++;
				  if(ready_queue[0] != null) {
					  in_cpu = ready_queue[0];
		        	    id = ready_queue[0].pid;
		        	    ready_queue[0] = null;
		        	    cpu_busy = true;
		        	    for(int i = 0; i < in_queue; i++){
		        	      ready_queue[i] = ready_queue[i+1];
		        	      ready_queue[i+1] = null;
		        	    }
		        	    in_queue--;
				  }// Processes are preempted if they exceed the quantum
			  }else if(processes[id].quantum == 0 & time != 0){
		    	    if(ready_queue[0].burst_cpu > 0){
		     	       ready_queue[in_queue] = in_cpu;
		     	       processes[id].quantum = quantum;
		     	       in_cpu = ready_queue[0];
		     	       id = ready_queue[0].pid;
		     	       ready_queue[0] = null;
		     	       cpu_busy = true;
		     	       for(int i = 0; i < in_queue; i++){
		     	    	   ready_queue[i] = ready_queue[i+1];
		     	    	   ready_queue[i+1] = null;
		     	       }
		     	     }// If the CPU has never been used 
		     }else if(cpu_busy == false){
		    	 if(ready_queue[0].burst_cpu > 0){
		      	   in_cpu = ready_queue[0];
		      	   id = ready_queue[0].pid;
		      	   ready_queue[0] = null;
		      	   in_queue--;
		      	   cpu_busy = true;

		      	 }

		     }

			  
			  processes[id].remaining_time--;
			  processes[id].quantum--;
			  time++;
		  }
		  
		  
	  }
	  
	  
	  public static void print_results(RoundRobin[] processes) {
		  	float avg_turn_around = 0;
			float avg_waiting = 0;
			
			// Calculating the turn around and waiting time for each process
			for(int i = 0; i < processes.length; i++){
				processes[i].turn_around = processes[i].completed_time - processes[i].arrival_time;
				processes[i].waiting_time = processes[i].turn_around - processes[i].burst_cpu;
			}
			
			
			// Printing the values for each of the processes
			System.out.println("Processes "+" Arrival Time "+" Burst Time "+"Completion Time "+"Turn Around Time "+" Waiting Time");
			
			for(int i = 0; i < processes.length; i++){
				System.out.println("P"+(i+1)+"\t\t"+processes[i].arrival_time+"\t\t "+processes[i].burst_cpu+"\t\t "+processes[i].completed_time+"\t\t "+processes[i].turn_around+"\t\t "+processes[i].waiting_time);
			}
			
			// We calculate the average turn around and waiting time
			for(int i = 0; i < processes.length; i++){
				avg_turn_around = avg_turn_around + processes[i].turn_around;
				avg_waiting = avg_waiting + processes[i].waiting_time;
			}
			
			// Printing those values
			System.out.println("Average Turn Around: "+avg_turn_around/processes.length+"\t\t"+"Average Waiting Time: "+avg_waiting/processes.length);
		  
	  }

	public static void main(String[] args) {
		int quantum = 3;
		RoundRobin p1 = new RoundRobin();
		RoundRobin processes[] = new RoundRobin[5];
		processes[0] = new RoundRobin(0,0,7);
		processes[1] = new RoundRobin(1,2,4);
		processes[2] = new RoundRobin(2,4,1);
		processes[3] = new RoundRobin(3,5,4);
		processes[4] = new RoundRobin(4,6,4);
		p1.getCompTime(processes, quantum);
		print_results(processes);
		
		

	}

}
