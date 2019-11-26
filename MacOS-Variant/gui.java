import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.util.*;
import java.util.concurrent.TimeUnit;


class gui{
	static List<String> logged = new LinkedList<>();
    public static void main(String args[])throws Exception {
    	//Creating the Frame
        JFrame frame = new JFrame("CopernicuNetwork");
        JFrame frame2 = new JFrame("Ping Search");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setSize(700, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,950);
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
       
        
        //BUTTON AND PANEL CREATION TIME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel paneltop = new JPanel();
        JPanel panelbottom = new JPanel();
        JLabel range = new JLabel("Search range: ");
        range.setFont(new Font("Modern No. 20", Font.PLAIN, 27));
        JRadioButton narrow = new JRadioButton("Narrow");
        JRadioButton wide = new JRadioButton("Wide");
        ButtonGroup sweep = new ButtonGroup();
        wide.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        narrow.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        sweep.add(narrow);
        sweep.add(wide);
        JLabel scan = new JLabel("Port scanning: ");
        scan.setFont(new Font("Modern No. 20", Font.PLAIN, 27));
        JRadioButton on = new JRadioButton("On");
        JRadioButton off = new JRadioButton("Off");
        on.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        off.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        ButtonGroup port = new ButtonGroup();
        port.add(on);
        port.add(off);
        JLabel air = new JLabel("Air gap: ");
        air.setFont(new Font("Modern No. 20", Font.PLAIN, 27));
        
        JRadioButton yes = new JRadioButton("On");
        JRadioButton no = new JRadioButton("Off");
        yes.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        no.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
        ButtonGroup gap = new ButtonGroup();
        gap.add(no);
        gap.add(yes);
        JButton GoTime = new JButton("Search");
        
        JLabel search = new JLabel("Search Complete");
        search.setFont(new Font("Modern No. 20", Font.PLAIN, 36));
        JLabel fail = new JLabel("Network Error");
        fail.setFont(new Font("Modern No. 20", Font.PLAIN, 36));
        JButton GoDog = new JButton("Search & Enter WatchDog Mode");
        JLabel picLabel = new JLabel(new ImageIcon("src//tel.png"));
        
        //RADIO BUTTON ACTIONS!!!!!!!!!!!!!
     
        
        //IT'S GO TIME BABY!!!!!!!!!!!
        GoTime.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
            	
                int air_gap_flag;
                int port_scan_flag;
                int focused_sweep;
              //RADIO BUTTON ACTIONS!!!!!!!!!!!!!
                   if(on.isSelected()) {port_scan_flag = 1; }
                   else {port_scan_flag = 0;}
                   if(yes.isSelected()) {air_gap_flag = 1;}
                   else {air_gap_flag = 0;}
                   if(narrow.isSelected()) {focused_sweep = 1; JOptionPane.showMessageDialog(null, "Search is underway. This will take several minutes.");}
                   else {focused_sweep = 0;JOptionPane.showMessageDialog(null, "Search is underway. This will take several hours.");}

                   if(off.isSelected()) {port_scan_flag = 0; }

                   if(no.isSelected()) {air_gap_flag = 0;}

                   if(wide.isSelected()) {focused_sweep = 0;}

                   //System.out.println(air_gap_flag + "    ");
                   //System.out.print(port_scan_flag + "    ");
                   //System.out.print(focused_sweep + "    ");
                Multithread a = new Multithread();
                List<List<String>> logged = new LinkedList<>();


                //air_gap_flag

                // If set to 1 this variable will force a check in with the internet to ensure that the host machine is connected to the internet.
                // Set to 0 if the host machine is not connected to the internet. 

                //port_scan_flag

               // If set to 1 then every discoverd client on the host's network will have it's porst scanned. This will significantly increase run time.
                // If set to 0 then ports will not be scanned. 

                //focused_sweep

                // If set to 1 then a narrow sweep will be conducted, searching the local IP addresses only directly neighboring the host machine's local address. 

                // Example: If the host machine is at address 192.168.1.25 then a sweep will be conducted from 192.168.1.1 to 192.168.255.255. 
                // Intended for contiguous class C networks. 

                // If set to 0 then a full sweep will be conducted, searching all addresses on a network. 
                // Example: If the host's machine is on 10.170.1.1 then a sweep will be conducted from 10.1.1.1 to 10.255.255.255. 

                // Intended for single class A networks. 


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		timeStamp = "CopernicuLog_" + timeStamp + ".txt";
		logged = a.search(air_gap_flag, port_scan_flag, focused_sweep);
                System.out.print("AND DONE! \n");
		try{
			FileWriter writer = new FileWriter(timeStamp, true);
			for (List<String> member : logged){
				for (String sub_member: member){

					writer.write(sub_member + " ");
				}
				writer.write("\r\n");
			}
			writer.close();		
		}catch (IOException we) {
			System.out.print("!!! \n");
        	    we.printStackTrace();
	        }
		int number_of_results = logged.size();
		JLabel log = new JLabel(number_of_results+" devices found. A log file has been created called " + timeStamp);
		log.setFont(new Font("Modern No. 20", Font.PLAIN, 17));

                        JLabel picLabel = new JLabel(new ImageIcon("src//sat.png"));
                        		paneltop.add(search);
                                panel2.add(picLabel);
                                panelbottom.add(log);
                                frame2.getContentPane().add(BorderLayout.NORTH, paneltop);
                frame2.getContentPane().add(BorderLayout.CENTER, panel2);
                frame2.getContentPane().add(BorderLayout.SOUTH, panelbottom);
                frame2.setVisible(true);
            }
        });
        
        GoDog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int air_gap_flag;
                int port_scan_flag;
                int focused_sweep;
              //RADIO BUTTON ACTIONS!!!!!!!!!!!!!
                   if(on.isSelected()) {port_scan_flag = 1; }
                   else {port_scan_flag = 0;}
                   if(yes.isSelected()) {air_gap_flag = 1;}
                   else {air_gap_flag = 0;}
                   if(narrow.isSelected()) {focused_sweep = 1; JOptionPane.showMessageDialog(null, "Search is underway. This will take several minutes.");}
                   else {focused_sweep = 0;JOptionPane.showMessageDialog(null, "Search is underway. This will take several hours.");}

                   if(off.isSelected()) {port_scan_flag = 0; }

                   if(no.isSelected()) {air_gap_flag = 0;}

                   if(wide.isSelected()) {focused_sweep = 0;}

                   //System.out.println(air_gap_flag + "    ");
                   //System.out.print(port_scan_flag + "    ");
                   //System.out.print(focused_sweep + "    ");
                Multithread a = new Multithread();
                List<List<String>> logged = new LinkedList<>();


                //air_gap_flag

                // If set to 1 this variable will force a check in with the internet to ensure that the host machine is connected to the internet.
                // Set to 0 if the host machine is not connected to the internet. 

                //port_scan_flag

               // If set to 1 then every discoverd client on the host's network will have it's porst scanned. This will significantly increase run time.
                // If set to 0 then ports will not be scanned. 

                //focused_sweep

                // If set to 1 then a narrow sweep will be conducted, searching the local IP addresses only directly neighboring the host machine's local address. 

                // Example: If the host machine is at address 192.168.1.25 then a sweep will be conducted from 192.168.1.1 to 192.168.255.255. 
                // Intended for contiguous class C networks. 

                // If set to 0 then a full sweep will be conducted, searching all addresses on a network. 
                // Example: If the host's machine is on 10.170.1.1 then a sweep will be conducted from 10.1.1.1 to 10.255.255.255. 

                // Intended for single class A networks. 


		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		timeStamp = "CopernicuLog_" + timeStamp + ".txt";
                logged = a.watch_dog(air_gap_flag, port_scan_flag, focused_sweep);
		int failure_flag = a.return_failure();

                System.out.print("AND DONE!");
		System.out.print(failure_flag);
		System.out.print("\n");
		try{
			FileWriter writer = new FileWriter(timeStamp, true);
			for (List<String> member : logged){
				for (String sub_member: member){

					writer.write(sub_member + " ");
				}
				writer.write("\r\n");
			}
			writer.close();		
		}catch (IOException we) {
        	    we.printStackTrace();
	        }
		int number_of_results = logged.size();
		int fail_flag = a.return_failure();
		JLabel picLabel = new JLabel();
		JLabel log = new JLabel(number_of_results+" devices found. A log file has been created called " + timeStamp);
		JLabel error = new JLabel("Connection interrupted. Please check your connection to the network and try again.");
		log.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
		error.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
						if(fail_flag == 0) {picLabel = new JLabel(new ImageIcon("src//dog.png")); panelbottom.add(log); paneltop.add(search);}
						else {picLabel = new JLabel(new ImageIcon("src//met.png")); panel2.add(fail); panelbottom.add(error);}
                                panel2.add(picLabel);
                                frame2.getContentPane().add(BorderLayout.NORTH, paneltop);
                frame2.getContentPane().add(BorderLayout.CENTER, panel2);
                frame2.getContentPane().add(BorderLayout.SOUTH, panelbottom);
                frame2.setVisible(true);
            }
        });
              
        //IP address labels
        JLabel address = new JLabel("Public IP Address is: " + systemipaddress);
        address.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        address.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
        JLabel address1 = new JLabel("System IP Address is: " + localhost.getHostAddress());
        //FORMATTING FORMATTING FORMATTING FORMATTING FORMATTING FORMATTING 
        address1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        address1.setFont(new Font("Modern No. 20", Font.PLAIN, 17));
        panel.add(address);
        panel.add(address1);
        range.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        scan.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        wide.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        narrow.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        on.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        off.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        air.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        yes.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        no.setAlignmentX(JRadioButton.CENTER_ALIGNMENT);
        GoTime.setAlignmentX(JButton.CENTER_ALIGNMENT);
        GoDog.setAlignmentX(JButton.CENTER_ALIGNMENT);
        picLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //NOW WERE ADDING BUTTONS!!!!!!!!!!!!!!!!!!!!!!!!!!
        panel.add(range);
        panel.add(narrow);
        panel.add(wide);
        panel.add(scan);
        panel.add(on);
        panel.add(off);
        panel.add(air);
        panel.add(yes);
        panel.add(no);
        panel.add(GoTime);
        panel.add(Box.createRigidArea(new Dimension(10, 20)));
        panel.add(GoDog);
        panel.add(Box.createRigidArea(new Dimension(10, 20)));
        panel.add(picLabel);
        
        //Adding Components to the frame.
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setBackground(Color.WHITE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   ______                                 __               .__                     _________ .__  .__  _____  ___.                                        .__  .__  __          
//  \      \   ______  _  __   ____   _____/  |_  ___________|__| ____    ____   /\  \_   ___ \|  | |__|/ ____\ \_ |__ _____ _______  _______   ____ _____  |  | |__|/  |_ ___.__.
//  /   |   \ /  _ \ \/ \/ / _/ __ \ /    \   __\/ __ \_  __ \  |/    \  / ___\  \/  /    \  \/|  | |  \   __\   | __ \\__  \\_  __ \ \_  __ \_/ __ \\__  \ |  | |  \   __<   |  |
// /    |    (  <_> )     /  \  ___/|   |  \  | \  ___/|  | \/  |   |  \/ /_/  > /\  \     \___|  |_|  ||  |     | \_\ \/ __ \|  | \/  |  | \/\  ___/ / __ \|  |_|  ||  |  \___  |
// \____|__  /\____/ \/\_/    \___  >___|  /__|  \___  >__|  |__|___|  /\___  /  \/   \______  /____/__||__|     |___  (____  /__|     |__|    \___  >____  /____/__||__|  / ____|
//        \/                     \/     \/          \/              \//_____/               \/                       \/     \/                     \/     \/               \/     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



class Multithread {

	public int all_threads_complete_flag = 0;
	public int total_network_failure = 0;
	public int total_failure = 0;    
	public static String displayInterfaceInformation(NetworkInterface netint){

        try {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                String returnout = "";
                String fullstop = ""; 
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
			//System.out.print(address);
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
                String full_stop = ""; 
                for (NetworkInterface netint : Collections.list(nets)){
                     
                    full_stop = netint.getDisplayName(); 
                    System.out.println("!"); 
                    if (full_stop.equals("en0")) {
                        str = displayInterfaceInformation(netint);
                        System.out.println(str); 
                        break; 
                    }
                }
		nums = str.split("\\.");
                System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	
	int first = Integer.parseInt(nums[0]);
    	int second = Integer.parseInt(nums[1]);
    	cores = 1; 
    	System.out.println("I got to cores"); 
	    // The cores are counted here
	    for (int i=1; i<cores+1; i++){
		    //Here each interation of the for loop opens up a new thread
		    System.out.println("Thread"); 
		    randomNumberTasks[i] = new Multithreading_ping(first, second, i, cores, port_scan_flag, sweep_flag);
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

        System.out.println("Exited thread start"); 
	    for (int i = 1; i < cores+1; i++){
		    int aa = 0;

		    aa = randomNumberTasks[i].is_done();
		    
		    // Here we check the network health. 
		    
		    while(aa != 1){ 
			    if(air_gap_flag == 0){
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
				    total_failure = 1;
				}
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
	
	all_threads_complete_flag = 1;    
	return result_data;
	    
    }

    public int return_failure(){
    	return total_failure;	
    }

    public List<List<String>> watch_dog(int air_gap_flag, int port_scan_flag, int sweep_flag){  
    	String ip = address();
    	String[] nums = ip.split("\\.");
	int cores = Runtime.getRuntime().availableProcessors();
	Multithreading_ping[] randomNumberTasks = new Multithreading_ping[cores+1];
	List<List<String>> result_data = new LinkedList<>();
	List<List<String>> log_data = new LinkedList<>();
	try {
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
                String str = "";
                for (NetworkInterface netint : Collections.list(nets)){
                    str = displayInterfaceInformation(netint);
                    break;
                }
		nums = str.split("\\.");
                System.out.printf(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
	int first = Integer.parseInt(nums[0]);
    	int second = Integer.parseInt(nums[1]);
	    // The cores are counted here
	    for (int i=1; i<cores+1; i++){
		    //Here each interation of the for loop opens up a new thread
		    randomNumberTasks[i] = new Multithreading_ping(first, second, i, cores, port_scan_flag, sweep_flag);
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
			    
			    if(air_gap_flag == 0){
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
				    total_failure = 1;
				}
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
	

	
	for(int i=0; i <= 5; i++){
		System.out.print("----------------------------------------------------------------------------\n");
		List<List<String>> update_data = new LinkedList<>();

		for (List<String> member : result_data){
			if(total_failure == 1){
				System.out.print("Network Lost. \n");
				return log_data;
				}	
			for (String sub_member: member){
			List<String> log_part = new LinkedList<>();

			
			try {
				InetAddress inet = InetAddress.getByName(sub_member);
				if (inet.isReachable(5000)) {
 					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					System.out.print("Conection confirmed: "+sub_member+"\n");
					log_part.add("Conection OK: "+sub_member+" Time:"+timeStamp+"\n");
					update_data.add(member);
					}
				else{

 					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					System.out.print("!!! NETWOKR ERROR !!! ------- "+sub_member+"\n");
					log_part.add("!!! WARNING !!! The conection to: "+sub_member+" was lost at time:" + timeStamp+"\n");
					String incident_time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					timeStamp = "CopernicuLog_INCIDENT_REPORT_" + timeStamp + ".txt";
					if(air_gap_flag == 0){
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
						    total_failure = 1;
						}
					}

					try{
						FileWriter incident_writer = new FileWriter(timeStamp, true);
						incident_writer.write("The conection to: "+sub_member+" was lost at time:" + timeStamp+"\n");
						incident_writer.write("\r\n");
						incident_writer.close();		
					}catch (IOException we) {
						System.out.print("!!! \n");
					    we.printStackTrace();
					}
					TimeUnit.SECONDS.sleep(3);
					}
				} catch (Exception e) {}
				log_data.add(log_part);
				break;
			}
		}
		result_data = update_data;
	
	if(total_failure == 1){
		break;
		}	
	try{
		TimeUnit.SECONDS.sleep(15);
		}catch(InterruptedException e){
		System.out.println("Caught in main."); 
		}
	}
	return log_data;
	
    }
}
class Multithreading_ping implements Runnable { 
	
	private Object result = null;
	
	private int start_var;
	private int inc_var;
	private int a_var;
	private int b_var;
	private int scan_flag;
	private int network_flag;
	private String ip;
		
    	public Multithreading_ping(int aaa, int bbb, int xxx, int yyy, int port_scan_flag, int sweep_flag){
		
		// Here the inputs for the ping are managed
    	 	
		start_var = xxx;
		inc_var = yyy;
		a_var = aaa;
		b_var = bbb;
		scan_flag = port_scan_flag;
		network_flag = sweep_flag; 
		
		}	
	List<List<String>> logged = new LinkedList<>();
	int complete_flag = 0;
	public void run() { 
        System.out.println("Entered public void run"); 
		if (network_flag == 1){	
			try{
				String s;

				String found = "Adresses discovered: \n";
				for(int c=1;c<=1;c++){
					for(int d=start_var;d<=255;d = d+ inc_var){

						String s_part1 = a_var +""; //Integer.toString(a);
						String s_part2 = b_var +"";
						String s_part3 = Integer.toString(c);
						String s_part4 = Integer.toString(d);
                        System.out.println("network_flag == 1"); 
						String ipAddress = s_part1 +"."+ s_part2+"."+s_part3+"."+s_part4;
						System.out.println(ipAddress); 
						try {
							InetAddress inet = InetAddress.getByName(ipAddress);
							if (inet.isReachable(5000)) {
								List<String> sub_log = new LinkedList<>();
								
								String hostname = inet.getHostName();

								// If the address is found to be recieving a ping the here a print statement will show the address pinged
								System.out.println("Found: " + s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
								sub_log.add(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4);
								if (scan_flag == 1){
								    System.out.println("Scan flag entered"); 
									for (int port = 1; port <= 15535; port++) {
									try{
										Socket socket = new Socket();
										socket.connect(new InetSocketAddress(s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4, port), 1000);
										socket.close();
										System.out.println("Port " + port + " is open on "+ s_part1 +"." +s_part2+"."+s_part3+"."+ s_part4 + " on Host Name: " + hostname);
										String a_port = "";
										a_port = Integer.toString(port);
										a_port = "Port "+ a_port;
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
			complete_flag = 1; 
			} 
			catch (Exception e){ 
			    // Throwing an exception 	
			}
		
		}else{
			try{
				String s;

				String found = "Adresses discovered: \n";
				//for(int a=a_var;a<=255;a++){
					for(int b=b_var;b<=255;b++){
						for(int c=1;c<=1;c++){
							boolean valid_range = false;
							for(int d=start_var;d<=255;d = d+ inc_var){

								String s_part1 = a_var +""; //Integer.toString(a);
								String s_part2 = Integer.toString(b);
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
												a_port = "Port "+ a_port;
												System.out.println(a_port);
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
					//}
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
