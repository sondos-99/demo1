package com.example.demo.FileAccessServices.InfoFromUserIndex;

import java.util.*;

public class InfoFromIndex {

    public static String getKey(String line){
        return (line.split("=")[0].replaceAll("[{]*", ""));
    }
    public static String getId(String key){
        return key.split("\\+")[0];
    }

    public static Long getValue(String key){

        return Long.valueOf(key.split("=")[1].replaceAll("[}]*", ""));
    }

    public static String  getCity(String line){
        return line.split("\\+")[1];
    }

    public static Map<String , Long> createIndexByName(Map<String , Long> indexes){
        Map<String , Long> indexByName = new HashMap<>();
        for (String key : indexes.keySet()){
            indexByName.put(getId(key) ,indexes.get(key) );
        }
            return indexByName;
    }

    public static Map<String , ArrayList<Long>> createIndexByCity(Map<String , Long> indexes){
        Map<String , ArrayList<Long>> indexByName = new HashMap<>();
         ArrayList list;
        for (String key: indexes.keySet()){
            String n =getCity(key);
            if (indexByName.containsKey(n))
            {
                list = indexByName.get(n);
            }
            else
            {
                list=new ArrayList();
            }
            list.add(indexes.get(key));
            indexByName.put(getCity(key) ,list);
        }
        return indexByName;
    }
}
