package com.example.demo.FileAccessServices.Services;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileAccessService {
  private RandomAccessFile file;
  public FileAccessService(String fileName,boolean isAdmin)
  {
    try
    {
      if (isAdmin) {
        file = new RandomAccessFile(fileName, "rw");
      }
      else
      {
        file = new RandomAccessFile(fileName, "r");
      }
    } catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  public Long getLength () throws IOException {
    return file.length();
  }

  public String readFromRandomAccessFile(long position) throws IOException {
    file.seek(position);
    String str =file.readLine();
    return str;
  }

  public void writeToRandomAccessFile(String json , Long position) throws IOException{
    file.seek(position);
    String originalString = json + "\n";
    String updatedString = originalString.replace("\n", "\r\n");
    file.write(updatedString.getBytes());
  }

  public void writeToRandomAccessFile(String json) throws IOException {
    writeToRandomAccessFile(json,file.length());

  }
    }

