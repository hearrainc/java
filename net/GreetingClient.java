package com.run.net;

import java.net.*;
import java.io.*;

public class GreetingClient {
    public static void main(String [] args)
    {
       String serverName = args[0];
       int port = Integer.parseInt(args[1]);
       try
       {
          System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
          
          Socket client = new Socket(serverName, port); //connect
          System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
          
          OutputStream outToServer = client.getOutputStream(); //send msg
          DataOutputStream out = new DataOutputStream(outToServer);
          out.writeUTF("Hello from " + client.getLocalSocketAddress());
          
          InputStream inFromServer = client.getInputStream(); //revieve msg
          DataInputStream in = new DataInputStream(inFromServer);
          System.out.println("服务器响应： " + in.readUTF());
          
          client.close(); // disconnect
       }catch(IOException e)
       {
          e.printStackTrace();
       }
    }
}
