package com.example.demo.menuUsers;

import com.example.demo.ConverterJSON.ConverterJSON;
import com.example.demo.Model.Address;
import com.example.demo.Model.Admin;
import com.example.demo.Model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class menuUser {
    static Scanner sc = new Scanner(System.in);
    public static void showCities(Socket socket) throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                while (true) {

                    System.out.println("Enter City Name:");
                    String cityName = sc.nextLine();
                    System.out.println(cityName);
                    output.println(cityName);

                    if (cityName.equals("Quit")) break;
                }
    }

    public static void menuAdmin(Socket socket) throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("1- Create a New User  :");
        System.out.println("2- update a New User  :");
        System.out.println("3- Show Users Of Cities :");

        switch (sc.nextLine()){
            case "1" :
            {
                System.out.println("Id Name :");
                String idName =sc.nextLine();
                System.out.println("First Name :");
                String firstName =sc.nextLine();
                System.out.println("Last Name :");
                String lastName =sc.nextLine();
                System.out.println("password :");
                String password =sc.nextLine();
                System.out.println("Age :");
                String age =sc.nextLine();
                System.out.println("Gender :");
                String Gender =sc.nextLine();
                System.out.println("id City:");
                String idCity =sc.nextLine();
                System.out.println("Name City:");
                String cityName =sc.nextLine();
                System.out.println("Name Street:");
                String streetName =sc.nextLine();
                Address address = new Address(idCity,cityName,streetName);
                User user = new User(idName,firstName,lastName,password,age,Gender,address);
                output.println(ConverterJSON.convertToJSON(user) + "*add");
                break;
            }
            case "2":
        {
          System.out.println("update you password :");
          System.out.println("Enter your old password :");
          String oldPassword = sc.nextLine();
          System.out.println("Enter your New password :");
          String newPassword = sc.nextLine();

          output.println(oldPassword + "*update");
          output.println(newPassword+ "*update");
          break;
        }
            case "3":
            {
                showCities(socket);
                break;
            }

        }

    }



}
