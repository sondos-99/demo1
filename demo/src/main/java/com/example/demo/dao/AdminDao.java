package com.example.demo.dao;

import com.example.demo.ConverterJSON.ConverterJSON;
import com.example.demo.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class AdminDao extends UserDao{

    public AdminDao(String fileName , String indexFileName) throws IOException {
        super(fileName,indexFileName);
    }

    public void createNewUser (String json){
        ConverterJSON converterJSON = new ConverterJSON();
        User user = null;
        try {
            user = (User) converterJSON.convertFromJSON(User.class,json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String key = user.getId()+"+"+user.getAddress().getCity();

        super.userAccessServies.writeToFile(json,key);
    }

    public void updateUserPassword (User user , String password) throws IOException {
         user.setPassword(password);
        userAccessServies.updateAtFile(converterJSON.convertToJSON(user),user.getId());
    }

    public void updateUserAddress (User user , String city,String street) throws IOException {
        user.getAddress().setCity(city);
        user.getAddress().setStreet(street);
        userAccessServies.updateAtFile(converterJSON.convertToJSON(user),user.getId());

    }

    public void updateUserAddress(User user,String street) throws IOException {
        updateUserAddress(user , user.getAddress().getCity() ,street);
    }

}
