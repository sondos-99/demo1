package DatabaseSocket.Node2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SlaveServer1 {
    static final int PORT = 5001;

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

                ClientHandler1 clientSock
                        = new ClientHandler1(clientSocket);

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
