package com.example.demo.FileAccessServices.InfoFromUserIndex;

import java.util.*;

public class InfoFromIndex {

    public static String getKey(String line){
        return (line.split("=")[0].replaceAll("[{]*", ""));
    }

    public static Long getValue(String key){

        return Long.valueOf(key.split("=")[1].replaceAll("[}]*", ""));
    }


    public static Map<String , ArrayList<Long>> createIndexByValue(Map<String , Long> indexes){
        Map<String , ArrayList<Long>> indexByValue = new HashMap<>();
         ArrayList list;
        for (String key: indexes.keySet()){
            if (indexByValue.containsKey(key))
            {
                list = indexByValue.get(key);
            }
            else
            {
                list=new ArrayList();
            }
            list.add(indexes.get(key));
            indexByValue.put(key ,list);
        }
        return indexByValue;
    }
}
