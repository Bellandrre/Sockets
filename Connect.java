/**
 * @author Karthik Kolathumani
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Connect {

static int ActiveCore;
//stores terminal command output in an array list   
public List<String> getTerminalOutput(String command)throws Exception{

    Process p = java.lang.Runtime.getRuntime().exec(command);
    String line="";
    List<String> results = new ArrayList<String>();
    try (BufferedReader buf =
            new BufferedReader(new InputStreamReader(p.getInputStream()))) {
        while ((line = buf.readLine())!=null){
            results.add(line);
        }

    p.waitFor();    

    }catch (InterruptedException e){
        e.printStackTrace();
    }
    return results; 
}
   

	public static void main(String[] args) throws Exception {
		
        String serverOrClient = args[0];
	    String port = args[1];
        Connect core1 = new Connect();
        List<String> line = core1.getTerminalOutput("grep -c ^processor /proc/cpuinfo");//gets the number of cores
        Integer cores = Integer.parseInt(line.get(0));//convert cores to int

	    if(serverOrClient.equals("ts")) {
	    	System.out.println("TCP Sever Running");
	    	new TCPServer().runserver(port);
	    	
	    	}
	    if(serverOrClient.equals("tc")) {
	    	 String ip = args[2];
	    	new Thread(new Main(ip,port,serverOrClient)).start();
            
            List<String> line2=core1.getTerminalOutput("ps -C java -o pid");  //gets the list of processes executed by java
            for (int i=1; i<line2.size(); i++){
            ActiveCore +=1;
            java.lang.Runtime.getRuntime().exec("taskset -p -c" + ActiveCore%cores+ " "+ Integer.parseInt(line2.get(i).replaceAll("\\s+","")));//distributes processes evenly among cores. 
            
	    }   
	}
}

}
