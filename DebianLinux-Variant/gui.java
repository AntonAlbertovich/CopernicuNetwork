import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.*; 
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.*;
import static java.lang.System.out;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class gui{
	static List<String> logged = new LinkedList<>();
	private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
      }
	
	private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
            logged.add(cmd + " " + line);
        }
      }
	
    public static void main(String args[])throws Exception {
    	//Creating the Frame
        JFrame frame = new JFrame("CopernicuNetwork");
        JFrame frame2 = new JFrame("Ping Search");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
     // Returns the instance of InetAddress containing 
        // local host name and address 
        InetAddress localhost = InetAddress.getLocalHost(); 
        // Find public IP address 
        String systemipaddress = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            
	    systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) 
        
        { 
            systemipaddress = "Cannot Execute Properly"; 
        }
        System.out.print(systemipaddress);
        //String ipAddress = "" + localhost.getHostAddress();
        byte[] mac = null;
        
        NetworkInterface networky = NetworkInterface.getByInetAddress(localhost);
        //mac = networky.getHardwareAddress(localhost);
		
	//StringBuilder sb = new StringBuilder();
        //for (int i = 0; i < mac.length; i++) {
        //   sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
        //}
      
        JPanel panel = new JPanel(); // the panel is not visible in output
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel paneltop = new JPanel();
        JButton FIPS = new JButton("Focused IP Search");
        JButton WIPS = new JButton("Wide IP Search");
        JButton FP = new JButton("Focused IP Search and Port Scan");
        JButton WP = new JButton("Wide IP Search and Port Scan");
        JLabel search = new JLabel("Search Complete");
        search.setFont(new Font("Serif", Font.PLAIN, 36));
        BufferedImage myPicture = ImageIO.read(new File("tel.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        
        //FIPS action
        FIPS.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
	            	Multithread a = new Multithread();
	            	List<List<String>> logged = new LinkedList<>();
			

			int air_gap_flag = 1;
			
			// If set to 1 this variable will force a check in with the internet to ensure that the host machine is connected to the internet.
			// Set to 0 if the host machine is not connected to the internet. 

			int port_scan_flag = 1;

			// If set to 1 then every discoverd client on the host's network will have it's porst scanned. This will significantly increase run time.
			// If set to 0 then ports will not be scanned. 

			int focused_sweep = 1;
			
			// If set to 1 then a narrow sweep will be conducted, searching the local IP addresses only directly neighboring the host machine's local address. 
			
			// Example: If the host machine is at address 192.168.1.25 then a sweep will be conducted from 192.168.1.1 to 192.168.255.255. 
			// Intended for contiguous class C networks. 

			// If set to 0 then a full sweep will be conducted, searching all addresses on a network. 
			// Example: If the host's machine is on 10.170.1.1 then a sweep will be conducted from 10.1.1.1 to 10.255.255.255. 
			
			// Intended for single class A networks. 

			

			logged = a.search(air_gap_flag, port_scan_flag, focused_sweep);
			

			String somresult = "";
			somresult = a.return_result();
			System.out.print("AND DONE!");
			
			System.out.print(logged);
					
			//JLabel picLabel = new JLabel(new ImageIcon("C:\\Users\\sarah\\OneDrive\\Pictures\\memes\\sat.png"));
            		JLabel picLabel = new JLabel(new ImageIcon("sat.png"));
					panel2.add(picLabel);
					frame2.getContentPane().add(BorderLayout.NORTH, paneltop);
	            	frame2.getContentPane().add(BorderLayout.CENTER, panel2);
	            	frame2.setVisible(true);
	            	

				
            	//try {
            		
					//runProcess("java -cp src src\\Multithread.java");
					
				//} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				//}
	    }
        });
        
        //WIPS action
        WIPS.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            		JOptionPane.showMessageDialog(null, "This function takes several hours.", "Warning", JOptionPane.INFORMATION_MESSAGE);	
            		JLabel imgLabel = new JLabel(new ImageIcon("sat.png"));
    					
    					panel2.add(imgLabel);
    					frame2.getContentPane().add(BorderLayout.NORTH, paneltop);
    	            	frame2.getContentPane().add(BorderLayout.CENTER, panel2);
    	            	frame2.setVisible(true);
					
            }
        });
        
        //WP action 
        WP.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	//try {
            		JOptionPane.showMessageDialog(null, "This function takes several hours.", "Warning", JOptionPane.INFORMATION_MESSAGE);
					//BufferedImage pic = ImageIO.read(new File("C:\\Users\\sarah\\OneDrive\\Pictures\\memes\\sat.png"));
					//JLabel picLabel = new JLabel(new ImageIcon(pic));
					//frame2.add(picLabel);
	            	//frame2.setVisible(true);
	            	//frame2.getContentPane().add(BorderLayout.NORTH, panel);
	            	//frame2.getContentPane().add(BorderLayout.CENTER, panel2);
				//} catch (IOException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				//}
            	try {
            		
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        //IP address labels
        JLabel address = new JLabel("Public IP Address is: " + systemipaddress +"                                 ");
        JLabel address1 = new JLabel("System IP Address is: " + localhost.getHostAddress()+"                                 ");
        //JLabel address2 = new JLabel("Current MAC Address is: " + sb.toString()+"                                 ");
       
        //panel.add(MAC);
        panel1.add(address);
        panel1.add(address1);
        //panel1.add(address2);
        panel1.add(picLabel);
        panel.add(FIPS);
        panel.add(WIPS);
        panel.add(FP);
        panel.add(WP);
        paneltop.add(search);
        
        //Adding Components to the frame.
        //frame2.getContentPane().add(BorderLayout.NORTH, panel);
    	//frame2.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   ______                                 __               .__                     _________ .__  .__  _____  _____  ___.                                        .__  .__  __          
//  \      \   ______  _  __   ____   _____/  |_  ___________|__| ____    ____   /\  \_   ___ \|  | |__|/ ____\/ ____\ \_ |__ _____ _______  _______   ____ _____  |  | |__|/  |_ ___.__.
//  /   |   \ /  _ \ \/ \/ / _/ __ \ /    \   __\/ __ \_  __ \  |/    \  / ___\  \/  /    \  \/|  | |  \   __\\   __\   | __ \\__  \\_  __ \ \_  __ \_/ __ \\__  \ |  | |  \   __<   |  |
// /    |    (  <_> )     /  \  ___/|   |  \  | \  ___/|  | \/  |   |  \/ /_/  > /\  \     \___|  |_|  ||  |   |  |     | \_\ \/ __ \|  | \/  |  | \/\  ___/ / __ \|  |_|  ||  |  \___  |
// \____|__  /\____/ \/\_/    \___  >___|  /__|  \___  >__|  |__|___|  /\___  /  \/   \______  /____/__||__|   |__|     |___  (____  /__|     |__|    \___  >____  /____/__||__|  / ____|
//        \/                     \/     \/          \/              \//_____/               \/                             \/     \/                     \/     \/               \/     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



class Multithread {
	 public static String displayInterfaceInformation(NetworkInterface netint){

        try {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                String returnout = "";
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {

                    returnout = String.format("%s", inetAddress);
                }



                return returnout.substring(1);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        }


	String address(){
		InetAddress localhost;
		try {
			localhost = InetAddress.getLocalHost();
			String address = localhost.getHostAddress();
			System.out.print(address);
			return address;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}


    public List<List<String>> search(int air_gap_flag, int port_scan_flag, int sweep_flag){  
    	String ip = address();
    	String[] nums = ip.split("\\.");
	int cores = Runtime.getRuntime().availableProcessors();
	Multithreading_ping[] randomNumberTasks = new Multithreading_ping[cores+1];
	List<List<String>> result_data = new LinkedList<>();

	try {
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
                String str = "";
                for (NetworkInterface netint : Collections.list(nets)){
                    str = displayInterfaceInformation(netint);
                    break;
                }
		nums = str.split("\\.");
                out.printf(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	
	int first = Integer.parseInt(nums[0]);
    	int second = Integer.parseInt(nums[1]);
	int net_sweep = sweep_flag;
	



	    // The cores are counted here
	    

	    
	    for (int i=1; i<cores+1; i++){
		    //Here each interation of the for loop opens up a new thread
		    randomNumberTasks[i] = new Multithreading_ping(first, second, i, cores, sweep_flag);
		    Thread t = new Thread(randomNumberTasks[i]);
		    t.start();
 
		    
		    //Runnable r = new Multithreading_ping(first, second, i, cores);

		    // The two inputs correspont to where the pings will start on the range of 1 to 255
		    // A GUI implemented here could allow a user to pick here the range should start


		    // All theads run at the same time.
		    // A machine with 8 cores will run 8 threads
		    // A machine with 6 cores will run 6 threads
		    //new Thread(r).start(); 
	    }

	    for (int i = 1; i < cores+1; i++){
		    int aa = 0;

		    aa = randomNumberTasks[i].is_done();
		    
		    // Here we check the network health. 
		    
		    while(aa != 1){
			    
			    if(air_gap_flag == 1){
					String network_health = "OK \n";
					try{
					    URL url_name = new URL("https://www.youtube.com");


					    // reads system IPAddress 
					}
					catch (Exception e)

					{
					    network_health = "Cannot Execute Properly";
					}
				System.out.print(network_health);	
				}			    
			    try{

			
				

			
			TimeUnit.SECONDS.sleep(5);
			}
			catch(InterruptedException e){
				System.out.println("Caught in main."); 
			}
			aa = randomNumberTasks[i].is_done();
		    }
				
		List<List<String>> xx = new LinkedList<>();
		xx = randomNumberTasks[i].return_deep_result();
		result_data.addAll(xx);
	    }
	return result_data;
	    
    }
    public String return_result(){
	return "Search is Complete";
    }
}
class Multithreading_ping implements Runnable { 
	
	private Object result = null;
	
	private int start_var;
	private int inc_var;
	private int a_var;
	private int b_var;
	private int scan_flag;
	private int sweep_flag;

	private String ip;
		
    	public Multithreading_ping(int aaa, int bbb, int xxx, int yyy, int port_scan_flag, int network_sweep_flag){
		
		// Here the inputs for the ping are managed
    	 	
		start_var = xxx;
		inc_var = yyy;
		a_var = aaa;
		b_var = bbb;
		scan_flag = port_scan_flag;
		sweep_flag = network_sweep_flag;

		}	
	List<List<String>> logged = new LinkedList<>();
	public void run() {
		if(sweep_flag == 1){
			try{
				String s;

				String found = "Adresses discovered: \n";
				//for(int a=a_var;a<=255;a++){
					//for(int b=b_var;b<=255;b++){
						for(int c=1;c<=1;c++){
							boolean valid_range = false;
							for(int d=start_var;d<=255;d = d+ inc_var){

								String s_part1 = a_var +""; //Integer.toString(a);
								String s_part2 = b_var +"";//Integer.toString(b);
								String s_part3 = Integer.toString(c);
								String s_part4 = Integer.toString(d);

								String ipAddress = s_part1 +"."+ s_part2+"."+s_part3+"."+s_part4;
								
								try {
									InetAddress inet = InetAddress.getByName(ipAddress);
									if (inet.isReachable(5000)) {
										valid_range = true;
										List<String> sub_log = new LinkedList<>();
										
										String hostname = inet.getHostName();

										// If the address is found to be recieving a ping the here a print statement will show the address pinged
										

										System.out.println("Found: " + s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
										
										sub_log.add(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
										if (scan_flag == 1){
											for (int port = 1; port <= 15535; port++) {
											try{
												Socket socket = new Socket();
												socket.connect(new InetSocketAddress(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4, port), 1000);
												socket.close();
												System.out.println("Port " + port + " is open on "+ s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4 + " on Host Name: " + hostname);
												String a_port = "";
												a_port = Integer.toString(port);
												sub_log.add(a_port);
												

											}catch (Exception ex) {
												
												}

											}
										}
										logged.add(sub_log);

									}
									
									

									} catch (Exception e) {}

								
								}
							

							}

						
						
						//}
					//}
			complete_flag = 1; 
						
			} 
			catch (Exception e){ 
			    // Throwing an exception 	
			}

		}else{
		
			try{
				String s;

				String found = "Adresses discovered: \n";
				for(int a=a_var;a<=255;a++){
					for(int b=b_var;b<=255;b++){
						for(int c=1;c<=1;c++){
							boolean valid_range = false;
							for(int d=start_var;d<=255;d = d+ inc_var){

								String s_part1 = a_var +""; //Integer.toString(a);
								String s_part2 = b_var +"";//Integer.toString(b);
								String s_part3 = Integer.toString(c);
								String s_part4 = Integer.toString(d);

								String ipAddress = s_part1 +"."+ s_part2+"."+s_part3+"."+s_part4;
								
								try {
									InetAddress inet = InetAddress.getByName(ipAddress);
									if (inet.isReachable(5000)) {
										valid_range = true;
										List<String> sub_log = new LinkedList<>();
										
										String hostname = inet.getHostName();

										// If the address is found to be recieving a ping the here a print statement will show the address pinged
										

										System.out.println("Found: " + s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
										
										sub_log.add(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
										if (scan_flag == 1){
											for (int port = 1; port <= 15535; port++) {
											try{
												Socket socket = new Socket();
												socket.connect(new InetSocketAddress(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4, port), 1000);
												socket.close();
												System.out.println("Port " + port + " is open on "+ s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4 + " on Host Name: " + hostname);
												String a_port = "";
												a_port = Integer.toString(port);
												sub_log.add(a_port);
												

											}catch (Exception ex) {
												
												}

											}
										}
										logged.add(sub_log);

									}
									
									

									} catch (Exception e) {}

								
								}
							

							}

						
						
						}
					}
			complete_flag = 1; 
						
			} 
			catch (Exception e){ 
			    // Throwing an exception 	
			}

		}

	}
    public int is_done(){
	    return complete_flag;
    }

    public List<List<String>> return_deep_result(){	
	int size = logged.size();


	    return logged;
    }



}
