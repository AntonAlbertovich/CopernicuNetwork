// Java code for thread creation by implementing 
// the Runnable Interface 

// Java Program to Ping an IP address 
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class Multithreading_ping implements Runnable { 
	private int start_var;
	private int inc_var;
	private int a_var;
	private int b_var;

    	
    	public Multithreading_ping(int aaa, int bbb, int xxx, int yyy){
		
		// Here the inputs for the ping are managed
		
		start_var = xxx;
		inc_var = yyy;
		a_var = aaa;
		b_var = bbb;
		}	

	public void run() { 
        try{
	       String s;
	       Process p;
	
		for(int a=a_var;a<=255;a++){


			for(int b=b_var;b<=255;b++){
				for(int c=1;c<=255;c++){
					for(int d=start_var;d<=255;d = d+ inc_var){

						String s_part1 = Integer.toString(a);
						String s_part2 = Integer.toString(b);
						String s_part3 = Integer.toString(c);
						String s_part4 = Integer.toString(d);

						String ipAddress = "ping " + s_part1 +"."+ s_part2+"."+s_part3+"."+s_part4 + " -c 1" ;
						
						try {
							p = Runtime.getRuntime().exec(ipAddress);
							BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));
							p.waitFor();
							if (p.exitValue() == 0){

								// If the address is found to be recieving a ping the here a print statement will show the address pinged
								

								System.out.println ("Found: " + s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
							}
							p.destroy();
							} catch (Exception e) {}


						}
					}
				}
			}
	     
        } 
        catch (Exception e){ 
            // Throwing an exception 

	} 
    } 
} 


// Main Class 
class Multithread { 
    public static void main(String[] args)throws UnknownHostException, IOException{  


	    int cores = Runtime.getRuntime().availableProcessors();
	    // The cores are counted here


	    for (int i=1; i<cores+1; i++){

		    //Here each interation of the for loop opens up a new thread
		   				 
		    Runnable r = new Multithreading_ping(192, 168, i, cores);

		    // The two inputs correspont to where the pings will start on the range of 1 to 255
		    // A GUI implemented here could allow a user to pick here the range should start


		    // All theads run at the same time.
		    // A machine with 8 cores will run 8 threads
		    // A machine with 6 cores will run 6 threads
		    new Thread(r).start();

	    } 

	    
    }
}

