package DatabaseSocket.Node1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    static final int PORT = 3501;

    public static void main(String[] args)
    {
        ServerSocket server = null;

        try {

            server = new ServerSocket(PORT);
            server.setReuseAddress(true);


            while(true) {
                Socket clientSocket = server.accept();
                System.out.println("New clientSocket connected"
                        + clientSocket.getInetAddress()
                        .getHostAddress());

                ClientHandler clientSock
                        = new ClientHandler(clientSocket);

                new Thread(clientSock).start();

            }
        }
        catch (IOException e) {
            System.out.println("Error occurred in main: " + e.getStackTrace());
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }






  }
}
