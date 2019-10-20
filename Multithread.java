// Java code for thread creation by implementing 
// the Runnable Interface 

// Java Program to Ping an IP address 
import java.io.*;
import java.net.*;

class MultithreadingDemo_1 implements Runnable { 
	private int start_var;
	private int inc_var;
    public static void sendPingRequest(String ipAddress)
              throws UnknownHostException, IOException
      {
    InetAddress scanner = InetAddress.getByName(ipAddress);
    if (scanner.isReachable(50))
      System.out.println("host is reachable");

  	}
    	
    	public MultithreadingDemo_1(int xxx, int yyy){
		start_var = xxx;
		inc_var = yyy;
		}	

	public void run() { 
        try{ 
            // Displaying the thread that is running 
		for(int a=1;a<=255;a++){
			String r_part0 = "Scanning on range: ";
			String r_part1 = Integer.toString(a);
			String r_part2 = r_part0 + r_part1  + ".xxx.xxx.xxx";

      			System.out.println(r_part2);

			for(int b=1;b<=255;b++){
				for(int c=1;c<=255;c++){
					for(int d=start_var;d<=255;d = d+ inc_var){

						String s_part1 = Integer.toString(a);
						String s_part2 = Integer.toString(b);
						String s_part3 = Integer.toString(c);
						String s_part4 = Integer.toString(d);

						String ipAddress = s_part1 +"."+ s_part2+"."+s_part3+"."+s_part4;
						sendPingRequest(ipAddress);
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
class Multithread 
{ 
    public static void main(String[] args)throws UnknownHostException, IOException{  


	    int cores = Runtime.getRuntime().availableProcessors();
	    for (int i=1; i<cores+1; i++){
		    
		    Runnable r = new MultithreadingDemo_1(i, cores);
		    new Thread(r).start();
	    } 

	    
    }
}
