package com.example.demo.dao;

import com.example.demo.ConverterJSON.ConverterJSON;
import com.example.demo.FileAccessServices.InfoFromUserIndex.UserAccessServices;
import com.example.demo.Model.User;

import java.io.IOException;

public class UserDao implements UserDaoInterface{
    UserAccessServices userAccessServies;
    ConverterJSON converterJSON;
    User user;
    String json;

    public UserDao(String fileName , String indexFileName) throws IOException {
        userAccessServies = new UserAccessServices(fileName,indexFileName);
        converterJSON=new ConverterJSON();

    }

    public boolean isValidUser (String id,String password) throws IOException {
           json = userAccessServies.getUserByName(id);
           if (json == null)
           {
               System.out.println("its not a valid user !! Try again !");

               return false;
           }
           else
           {
               user = (User) converterJSON.convertFromJSON(User.class,json);
               if (user.getPassword().equals(password))
               {
                   return true;
               }
               else
               {
                   System.out.println("it's wrong password try again !!" + user.getPassword());
                   return false;
               }
           }
    }

    public String getUserInfo(){
          return json;
    }

    public boolean isUserAdmin(){
    System.out.println(user.getId());
        return user.getId().contains("A");
    }



        @Override
    public void findByCity(String cityName) {
            try {
                userAccessServies.getUserByCity(cityName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
