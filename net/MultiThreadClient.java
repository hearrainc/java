package com.run.net;

import java.net.*;
import java.io.*;

public class MultiThreadClient {
    public static void main(String [] args)
    {
       String serverName = args[0];
       int port = Integer.parseInt(args[1]);
       try
       {
          Socket client = new Socket(serverName, port);
          System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
          
          //send msg to s
          System.out.println("send msg to s.");
          PrintWriter pw = new PrintWriter(client.getOutputStream());
          pw.write(args[2]);
          pw.flush();
          
          client.shutdownOutput();
          //recieve msg from s
          BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
          String info = null;
          while((info=br.readLine()) != null){
               System.out.println("Server say:"+info);
          }
          
          br.close();
          pw.close();
          client.close();
       }catch(IOException e)
       {
          e.printStackTrace();
       }
    }
}
