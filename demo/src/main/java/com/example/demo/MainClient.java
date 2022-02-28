package com.example.demo;

import com.example.demo.ConsistentHashing.HandlerServerPorts;
import lombok.var;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

  public void newClientConnection(String clientIP) throws IOException {
    HandlerServerPorts handlerServerPorts = new HandlerServerPorts();
    final int PORT =handlerServerPorts.getPort(clientIP);

    try (Socket socket = new Socket("localhost", PORT)) {
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      Scanner sc = new Scanner(System.in);

      output.println("client");

      System.out.println("name of db ?");
      String dbName =sc.next();
      boolean flag = true;
      System.out.println("menu. . . ");
      System.out.println("0- if Exit !");
      System.out.println("1 -insert to db !");
      System.out.println("2 -read from db !");
      System.out.println("3 - update db !");
     while (flag) {
      switch (sc.nextInt()) {
        case 0 :
            {
              flag = false;
              output.println("exit");

              break;
           }
        case 1:
          {

              System.out.println("insert json? ..");
              String JSON = sc.next();
              output.println("insert_"+dbName +"_"+JSON);
              break;
          }

        case 2 :
        {
              System.out.println("Enter your id:value . . ");
              var id= sc.next();
               var value= sc.next();

            output.println("read_"+dbName+"_"+id+":"+value);
              System.out.println(input.readLine());
            break;
          }


          case 3 :
          {
              System.out.println("Enter your property and value you want to change in json form (with id) . . ");
            //{"id":"" , "name" : "j"}
              var json= sc.next();
              output.println("update_"+dbName+"_"+json);
              break;
          }
        }
     }


    } catch (Exception e) {
      System.out.println("Exception occurs in client main: " + e.getStackTrace());
    }

    finally{
    }
    }
  }


