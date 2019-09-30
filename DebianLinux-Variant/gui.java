import java.awt.BorderLayout;
import java.net.*; 
import java.io.*; 
import java.net.InetAddress; 
import javax.swing.*;
class gui{
    public static void main(String args[])throws Exception {
    	//Creating the Frame
        JFrame frame = new JFrame("CopernicuNetwork");
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
        InetAddress add = InetAddress.getByName("Juice");
        NetworkInterface networky = NetworkInterface.getByInetAddress(add);
        //mac = networky.getHardwareAddress();
		
		StringBuilder sb = new StringBuilder();
        //for (int i = 0; i < mac.length; i++) {
           //sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
        //}
      
        JPanel panel = new JPanel(); // the panel is not visible in output
        JPanel panel1 = new JPanel();
        JButton IP = new JButton("IP Address");
        JButton MAC = new JButton("MAC Address");
        JLabel address = new JLabel("Public IP Address is: " + systemipaddress);
        JLabel address1 = new JLabel("System IP Address is: " + localhost.getHostAddress());
        //JLabel address2 = new JLabel("Current MAC Address is: " + sb.toString());
        //panel.add(IP);
        //panel.add(MAC);
        panel1.add(address);
        panel1.add(address1);
        //panel1.add(address2);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }
}
