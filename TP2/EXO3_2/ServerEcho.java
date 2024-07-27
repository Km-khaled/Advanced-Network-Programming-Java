/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EXO3_2;
import java.net.*;
import java.io.*;
public class ServerEcho {
     public static void main(String args[]) {
           ServerSocket server = null;
            try {
      server = new ServerSocket(7777); 
      while (true) {
        Socket sock = server.accept(); 
        System.out.println("connecte"); 
              T thread = new T(sock);  
             thread.start(); 
      }
     }catch (IOException e) {}
}
}
