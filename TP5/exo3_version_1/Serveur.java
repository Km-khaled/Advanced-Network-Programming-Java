package exo3_version_1;

import java.io.*;

import java.net.*;

public class Serveur{

public static void main(String []args) throws IOException {

Socket sock = new ServerSocket(9001).accept();

ABC.mystere(sock.getInputStream(),new FileOutputStream("./src/Exo3/test2.txt"));

sock.close();

}

}