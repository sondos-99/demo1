package com.example.demo.FileAccessServices.Services;
import com.example.demo.FileAccessServices.InfoFromUserIndex.InfoFromIndex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class IndexAccessService {

    private Map<String , Long> fields;
    private RandomAccessFile file;
    public IndexAccessService(String fileName, boolean isAdmin){
        fields= new HashMap();

        try {
            if (isAdmin)
                 file = new RandomAccessFile(fileName, "rw");
            else
                file = new RandomAccessFile(fileName, "r");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String , Long> readFromIndexFile() throws IOException {
        String line;
        do{
            line = file.readLine();
            if(line!=null){

                String key = InfoFromIndex.getKey(line);
                Long value = InfoFromIndex.getValue(line);
                fields.put(key, value);
            }
        }

        while(line!=null);
   return fields;
    }

    public void writeFileIndex(String key,Long value) throws IOException {
        Map<String , Long> fieldWrite= new HashMap();

        fieldWrite.put(key,value);
        file.seek(file.length());

        String originalString = fieldWrite + "\n";
        String updatedString = originalString.replace("\n", "\r\n");
        file.write(updatedString.getBytes());
        file.close();
    }

}
