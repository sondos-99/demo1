package com.example.demo.Model;

public class Client extends User {
    public Client(String idName,
                  String firstName,
                  String lastName,
                  String password,
                  String age,
                  String gender,
                  Address address) {
        super(idName, firstName, lastName, password, age, gender, address);
    }
    public Client(){}
}
