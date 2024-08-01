package exo3_version_1;

import java.io.*;

import java.net.*;

public class Client {

public static void main(String []args) throws IOException {

Socket sock = new Socket("localhost",9001);

ABC.mystere(new FileInputStream("./src/Exo3/test1.txt"),sock.getOutputStream());

sock.close();

}

}




