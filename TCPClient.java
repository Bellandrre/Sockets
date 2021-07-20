package genericnode;

import java.io.*;
import java.net.*;

/**
 *
 * @author Karthik Kolathumani
 */
public class TCPClient {

    public static void runClient(String ip, String port, String command, String key, String value) throws Exception {

        String reply;
        Socket clientSocket = new Socket(ip, Integer.valueOf(port));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(command + '\n');
        outToServer.writeBytes(key + '\n');
        outToServer.writeBytes(value + '\n');
        
        reply = inFromServer.readLine();
        outToServer.close();
        System.out.println("server response: " + reply);
        clientSocket.close();
      
    }

    public static void runClient2(String ip, String port, String command) throws Exception {
    	
        String reply;
        Socket clientSocket = new Socket(ip, Integer.valueOf(port));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(command + '\n');
        reply = inFromServer.readLine();
        outToServer.close();
        inFromServer.close();
        System.out.println("server response: " + reply);
        clientSocket.close();
    }

}
