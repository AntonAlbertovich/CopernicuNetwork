import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*; 
import java.io.*; 
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
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
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        //Creating the MenuBar and adding components
        /*
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
		*/
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
        
        String ipAddress = "" + localhost.getHostAddress();
        byte[] mac = null;
        
        NetworkInterface networky = NetworkInterface.getByInetAddress(localhost);
        mac = networky.getHardwareAddress();
		
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
           sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
        }
      
        JPanel panel = new JPanel(); // the panel is not visible in output
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JButton IPS = new JButton("IP Search");
        JLabel search = new JLabel("Searching...");
    	
        
        IPS.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
          
            	frame2.setVisible(true);
            	try {
            		
					runProcess("java -cp src src\\Multithread.java");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        JLabel address = new JLabel("Public IP Address is: " + systemipaddress);
        JLabel address1 = new JLabel("System IP Address is: " + localhost.getHostAddress());
        JLabel address2 = new JLabel("Current MAC Address is: " + sb.toString());
       
        //panel.add(MAC);
        panel1.add(address);
        panel1.add(address1);
        panel1.add(address2);
        panel.add(IPS);
        panel2.add(search);
        
        //Adding Components to the frame.
        frame2.getContentPane().add(BorderLayout.NORTH, panel);
    	frame2.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }
}
