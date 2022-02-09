package DatabaseSocket.Node2;

import com.example.demo.dao.UserDao;
import java.io.*;
import java.net.*;

public class ClientHandler1 implements Runnable {
    private final Socket clientSocket;
  UserDao userDao =
      new UserDao(
          "C:\\Users\\sondo\\Music\\demo\\Node1\\Node1user.txt",
          "C:\\Users\\sondo\\Music\\demo\\Node1\\Node1index.txt");

    public ClientHandler1(Socket socket) throws IOException {
        this.clientSocket = socket;
    }

    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String idClient;
            String password;


            while (((idClient = in.readLine())  != null) && (password = in.readLine()) !=null) {

                if (userDao.isValidUser(idClient,password))
                {
                    out.println("valid");

                    break;
                }
                else
                {out.println("not valid");}

            }

            while (true){
                String nameOfCity = in.readLine();
                userDao.findByCity(nameOfCity);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
