package DatabaseSocket.Node1;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.ClientDao;
import com.example.demo.dao.UserDao;
import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private String fileName ="C:\\Users\\sondo\\Music\\demo\\user.txt";
    private String indexFileName = "C:\\Users\\sondo\\Music\\demo\\index.txt";
  UserDao userDao =
      new UserDao(
              fileName,
              indexFileName);

    AdminDao adminDao;
    ClientDao clientDao;

    public ClientHandler(Socket socket) throws IOException {
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
                            out.println(userDao.getUserInfo());
                            if (userDao.isUserAdmin()){
                                out.println("Admin");
                                 adminDao = new AdminDao(fileName,indexFileName);
                            }
                            else
                                out.println("Not");
                                 clientDao = new ClientDao(fileName,indexFileName);

                            break;
                        }
                        else
                        {
                            System.out.println("not valid");
                        }

            }
            out.flush();



      while (true) {
        if (userDao.isUserAdmin()) {

          while (true) {
            String nameOfCity = in.readLine();
            userDao.findByCity(nameOfCity);
          }
        }

        else {
            String valueFromAdmin = in.readLine();
            if (valueFromAdmin.contains("add")){
                 adminDao.createNewUser(valueFromAdmin);
            }
            else {
                while (true) {
                    String nameOfCity = in.readLine();
                    userDao.findByCity(nameOfCity);
                }
            }
        }
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
