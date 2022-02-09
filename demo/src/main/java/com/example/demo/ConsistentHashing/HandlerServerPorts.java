package com.example.demo.ConsistentHashing;

import com.example.demo.FileAccessServices.InfoFromUserIndex.InfoFromIndex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HandlerServerPorts {
    public int getPort (String userIP) throws IOException {
        int port;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(userIP+".txt"))){
            String str = br.readLine();
            port = Math.toIntExact(InfoFromIndex.getValue(str));
        }

        catch (IOException e) {
            ConsistentHashing consistentHashing = new ConsistentHashing();
            port=Integer. parseInt(consistentHashing.getServer(userIP));
            Map<String , Integer> hash = new HashMap<>();
            hash.put(userIP,port);
            Files.write(Paths.get("./"+userIP+".txt"), String.valueOf(hash).getBytes());
        }

        return port;
    }

}
