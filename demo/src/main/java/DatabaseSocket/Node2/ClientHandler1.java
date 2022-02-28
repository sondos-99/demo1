package DatabaseSocket.Node2;

import com.example.demo.FileAccessServices.ReadJSON;
import com.example.demo.FileAccessServices.WriteJSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;

public class ClientHandler1 implements Runnable {
    private final Socket clientSocket;
    private static String PATH ="C:\\Users\\sondo\\Music\\demo\\Node1\\";


    public ClientHandler1(Socket socket) throws IOException {
        this.clientSocket = socket;
    }
    public void run()
    {

        BufferedReader in = null;
        try {



            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            boolean flag = true;

            while (flag){
                String message = in.readLine();
                if (message.equals("exit")){
                    flag = false;
                }
                else
                {
                    crud(message);
                }
            }




        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        finally {
            try {

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

    public void crud (String message) throws ParseException, IOException {
        boolean flag = false;
//        FileInputStream fis=new FileInputStream(PATH+"database.json");
//        Scanner sc=new Scanner(fis);
        PrintWriter out = null;
        out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        String[] arrOfStr = message.split("_", 5);
        //check if the database exist
//        while(sc.hasNextLine())
//        {
//                  if (sc.nextLine ().equals(arrOfStr[1])){
//                flag = true;
//                break;
//            }
//        }


        if (arrOfStr[0].equals("insert"))
        {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(arrOfStr[2]);
            WriteJSON.writeJSON(jsonObject , PATH +arrOfStr[1]);
//            if (!flag)
//            {
//                FileAccessService.getInstance(PATH + "database.json").writeToRandomAccessFile(arrOfStr[1]);
//            }
        }

        else if (arrOfStr[0].equals("read"))
        {
            out.println( ReadJSON.readingJSON(PATH +arrOfStr[1], arrOfStr[2]));
        }

    }
}
