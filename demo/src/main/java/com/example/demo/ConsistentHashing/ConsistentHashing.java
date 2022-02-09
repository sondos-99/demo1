package com.example.demo.ConsistentHashing;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing{
    private static String[] servers = { "5001", "3501"};

    private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

    static {
        for (int i=0; i<servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]Join the collection, his Hash The value is" + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println();
    }

    private static int getHash(String str) {
        final int p = 1677;
        int hash =  211;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
    public static String getServer(String key) {
        int hash = getHash(key);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if(subMap.isEmpty()){
            Integer i = sortedMap.firstKey();
            return sortedMap.get(i);
        }else{
            Integer i = subMap.firstKey();
            return subMap.get(i);
        }
    }

}
