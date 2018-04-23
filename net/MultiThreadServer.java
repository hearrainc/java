package com.run.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable{
    Socket csocket;
    MultiThreadServer(Socket csocket) {
       this.csocket = csocket;
    }
  
    public static void main(String args[]) 
    throws Exception {
       ServerSocket ssock = new ServerSocket(1234);
       System.out.println("Listening");
       while (true) {
          Socket sock = ssock.accept();
          System.out.println("Connected");
          System.out.println("Remote-ip:" + sock.getRemoteSocketAddress());
          new Thread(new MultiThreadServer(sock)).start();
       }
       
    }
    public void run() {
       try {
         //recieve msg from c
           BufferedReader br = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
           String info = null;
           while((info=br.readLine()) != null){
              System.out.println("client say:"+info);
           }
           
           csocket.shutdownInput();
           //send msg to c
           System.out.println("send msg to c.");
           PrintWriter pw = new PrintWriter(csocket.getOutputStream());
           pw.write("Welcome!");
           pw.flush();
           
           pw.close();
           br.close();
           Thread.sleep(10000);
           System.out.println(Thread.currentThread().getName() + "end.");
           csocket.close();
       }
       catch (IOException e) {
          System.out.println(e);
       }
       catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}
