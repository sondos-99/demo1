package com.example.demo.FileAccessServices.InfoFromUserIndex;

import com.example.demo.FileAccessServices.Services.MainAccessServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UserAccessServices extends MainAccessServices {
    public UserAccessServices(String fileName, String fileIndexName) throws IOException {
        super(fileName, fileIndexName);
    }

    public String getUserByName(String id) throws IOException{
        Map<String ,Long> indexByName;
        indexByName = InfoFromIndex.createIndexByName(indexes);

        Long index = indexByName.get(id);
        if (index != null)
        {
            return fileAccessService.readFromRandomAccessFile(index);
        } else {
            System.out.println("User not found !!");
            return null;
        }
    }


    public void getUserByCity(String cityName) throws IOException{
        Map<String , ArrayList<Long>>indexByCity = InfoFromIndex.createIndexByCity(indexes);
        System.out.println("Hello From " + cityName);

        for (Long value :indexByCity.get(cityName)) {
            System.out.println(fileAccessService.readFromRandomAccessFile(value));
        }
    }



}
