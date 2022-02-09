package com.example.demo.FileAccessServices.Services;

import com.example.demo.ConverterJSON.ConverterJSON;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

public class MainAccessServices {
    protected FileAccessService fileAccessService;
    IndexAccessService indexAccessService;
    protected final Map<String , Long> indexes;

    public MainAccessServices(String fileName, String  fileIndexName) throws IOException {
        fileAccessService = new FileAccessService(fileName,true);
        indexAccessService = new IndexAccessService(fileIndexName,true);
        indexes=indexAccessService.readFromIndexFile();
    }

    public void writeToFile (String jsonToFile , String keyToIndex )
    {
        try {
            Long length =fileAccessService.getLength();
            indexAccessService.writeFileIndex(keyToIndex,length);
            fileAccessService.writeToRandomAccessFile(jsonToFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateAtFile (String jsonToFile , String key) throws IOException {
        for (String index :indexes.keySet() ) {
             if (index.contains(key)){
                 fileAccessService.writeToRandomAccessFile(jsonToFile , indexes.get(index));
                 break;
             }

        }
    }


    public static void copy(File main, File copy){
        try{
            RandomAccessFile data = new RandomAccessFile(main,"rw");
            RandomAccessFile dataCopy = new RandomAccessFile(copy,"rw");

            dataCopy.seek(0);
            for(int i = 0; i < main.length(); i++){
                dataCopy.write(data.read());
            }
        }catch(IOException e){
        }
    }

    public static void copyDataBase(String pathUser , String pathIndex){
        File original = new File("user.txt");
        File copy = new File(pathUser);
        copy(original,copy);
        File originalIndex = new File("index.txt");
        File copyIndex = new File(pathIndex);
        copy(originalIndex,copyIndex);
    }


}
