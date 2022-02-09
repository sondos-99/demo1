package com.example.demo.dao;

import java.io.IOException;

public class ClientDao extends UserDao{
    public ClientDao(String fileName , String indexFileName) throws IOException {
        super(fileName,indexFileName);
    }

}
