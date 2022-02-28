package DatabaseSocket.Node2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SlaveServer1 {
    static final int PORT = 5001;
    static final int PrimaryPORT = 3501;
    static Queue<String> qServer
            = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    ServerSocket server = null;
    try (Socket socket = new Socket("localhost", PrimaryPORT)) {
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      Scanner scanner = new Scanner(System.in);
      String massage = "{\"Server\":\"server\",\"port\":5001,\"path\":\"C:\\Users\\sondo\\Music\\demo\\Node1\"}";
      output.println(massage);

          try {

              server = new ServerSocket(PORT);
              server.setReuseAddress(true);

              int numOfClient = 10;
              int num = 0;

              while (num < numOfClient) {
                  Socket clientSocket = server.accept();

                  System.out.println(
                          "New clientSocket connected" + clientSocket.getInetAddress().getHostAddress());

                  ClientHandler1 clientSock = new ClientHandler1(clientSocket);

                  new Thread(clientSock).start();
                  num++;
              }

          } catch (IOException e) {
              System.out.println("Error occurred in main: " + e.getStackTrace());
              e.printStackTrace();
          } finally {
              if (server != null) {
                  try {
                      server.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }


    } catch (Exception e) {
      System.out.println("Exception occurs in client main: " + e.getStackTrace());
    }


}
    public static void setQue (String message){
        qServer.add(message);

    }

    public static String getQue (){
        return qServer.peek();
    }
}
