package com.example.demo;

import com.example.demo.ConsistentHashing.ConsistentHashing;
import com.example.demo.ConsistentHashing.HandlerServerPorts;
import com.example.demo.ConverterJSON.ConverterJSON;
import com.example.demo.Model.Address;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Client;
import com.example.demo.Model.User;
import com.example.demo.menuUsers.menuUser;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClient {

  public void newClientConnection(String clientIP) throws IOException {
    HandlerServerPorts handlerServerPorts = new HandlerServerPorts();
    final int PORT =handlerServerPorts.getPort(clientIP);

    try (Socket socket = new Socket("localhost", PORT)) {
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

      Scanner scanner = new Scanner(System.in);
      String idClient = null;
      String password;

      while (!"exit".equalsIgnoreCase(idClient)) {
        System.out.println("Enter your first name :");
        idClient = scanner.nextLine();
        System.out.println("Enter Password :");
        password = scanner.nextLine();

        output.println(idClient);
        output.println(password);

        output.flush();
        String infoOfUser ;
        if ((infoOfUser=input.readLine()) !=null)
        {
          System.out.println("It's a Valid User !!");
          ConverterJSON converterJSON= new ConverterJSON();
          if (input.readLine().equals("Admin"))
          {
            Admin admin = (Admin) converterJSON.convertFromJSON(Admin.class,infoOfUser);
            System.out.println("Hello "+admin.getFirstName());
            menuUser.menuAdmin(socket);

          }
          else
          {
            Client client = (Client) converterJSON.convertFromJSON(Client.class,infoOfUser);
            System.out.println("Hello "+client.getFirstName());
            menuUser.showCities(socket);
          }
          break;
        }
       else
        System.out.println("It's not a Valid User !!");
        }

      scanner.close();

    } catch (Exception e) {
      System.out.println("Exception occurs in client main: " + e.getStackTrace());
    }
  }
   }

