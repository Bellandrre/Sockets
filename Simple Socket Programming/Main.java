package genericnode;

/**
 *  Created by Farhad, Nursultan edited the original code and added UDP
 * @author Karthik Kolathumani
 * @author Nursultan
 */
public class Main {

  public static void main(String[] args) throws Exception {

    String serverOrClient = args[0];

    String port = args[1];

    String command;

    String key;

    String value;

    // SWITCH BOARD TO EITHER RUN TCP SERVER/CLIENT, UDP SERVER/CLIENT  

    switch (serverOrClient) {

    case "ts":

      System.out.println("TCP SERVER is running");

      new TCPServer().runserver(port);

      break;

    case "tc":

      if (args.length < 4) {

        System.err.println("Expected format: java GenericNode <protocol>  <port number> <server ip> <operation>");

        System.exit(1);

      }

      command = args[3];

      String ip = args[2];

      if (command != null) {

        switch (command) {

        case "put":

          key = args[4];

          value = args[5];

          new TCPClient().runClient(ip, port, command, key, value);
          break;

        case "del":

        case "get":

          key = args[4];

          value = null;

          new TCPClient().runClient(ip, port, command, key, value);

          break;

        case "store":

        case "exit":

          new TCPClient().runClient2(ip, port, command);

          break;

        default:

          System.out.println("\n WRONG COMMAND!\n");
          break;

        }

        //break for OPERATIONS

        break;

      }

      default:

        System.out.println("\n First parameter must either be tc ts uc or us\n");

    }

  }

}