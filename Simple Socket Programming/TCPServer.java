package genericnode;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Karthik Kolathumani
 * 
 */
public class TCPServer {

    DataStore ds = new DataStore();

    public void runserver(String port) throws Exception {
        String command;
        ServerSocket welcomeSocket = new ServerSocket(Integer.valueOf(port));
        Boolean On = true;
        Outer:
        while (On=true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            command = inFromClient.readLine();
            switch (command) {
                case "put":
                    String key = inFromClient.readLine();
                    String value = inFromClient.readLine();
                    ds.put(key, value);
                    outToClient.writeBytes("put key=" + key);
                    System.out.println("Received: " + command + " key: " + key + " value: " + value);
                    inFromClient.close();
                    break;
                case "get":
                    key = inFromClient.readLine();
                    String myVal = ds.get(key);
                    outToClient.writeBytes("get key=" + key + " value: " + myVal);
                    System.out.println("get key=" + key + " val=" + myVal);
                    inFromClient.close();
                    break;
                case "del":
                    key = inFromClient.readLine();
                    ds.map.remove(key);
                    System.out.println("delete key=" + key);
                    inFromClient.close();
                    break;
                case "store":
                    Map<String, String> map = ds.map;
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        System.out.println("STORE key:" + entry.getKey() + ":value:" + entry.getValue());
                    }
                    inFromClient.close();
                    break;
                case "exit":
                	outToClient.writeBytes("Connection to Server Closed");
                	outToClient.close();
                	inFromClient.close();
                	welcomeSocket.close();
                	On=false;
                    break Outer;
                default:
                    outToClient.writeBytes("Command not understood!");
            }
        }
    }

}
