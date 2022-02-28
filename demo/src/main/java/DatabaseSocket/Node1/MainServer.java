package DatabaseSocket.Node1;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MainServer {
    static final int PORT = 3501;
    static Queue<String> qOperation
            = new LinkedList<>();

    static boolean flag =false ;

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;

        try {

            server = new ServerSocket(PORT);
            server.setReuseAddress(true);


            int numOfClient = 10;
            int num =0;
            while(num<numOfClient) {

                Socket clientSocket = server.accept();
                System.out.println("New clientSocket connected"
                        + clientSocket.getInetAddress()
                        .getHostAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String massege = input.readLine();

                if (massege.equals("client")){
                       ClientHandler clientSock
                            = new ClientHandler(clientSocket);
                        new Thread(clientSock).start();
                }

                else
                {
                    ServerHandler.addNewNode(massege);
                    ServerHandler serverHandler = new ServerHandler(clientSocket , massege);
                    new Thread(serverHandler).start();
                }

               num++;
            }

        }

        catch (IOException | ParseException e) {
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

  }

  public static void setQue (String message){
         qOperation.add(message);

    }
    public static String getQue (){
       return qOperation.peek();
    }
    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        MainServer.flag = flag;
    }

}
