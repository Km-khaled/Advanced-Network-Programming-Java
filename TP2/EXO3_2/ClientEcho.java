package EXO3_2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientEcho {

    public static void main(String[] args) throws IOException {
        Socket sock = null;
        OutputStream sockOut = null;
        InputStream sockIn = null;
        try {
            sock = new Socket("localhost", 7777);
            sockOut = sock.getOutputStream();
            sockIn = sock.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(1);
        }
        byte[] buffer1 = new byte[1024];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre phrase : ");
        String phrase = scanner.nextLine();
        buffer1 = phrase.getBytes();
        try {
            sockOut.write(buffer1);
            sockOut.flush();
        } catch (IOException e) {
        }
        byte[] buffer2 = new byte[1024];
        int lu = sockIn.read(buffer2);
        String serverResponse = new String(buffer2, 0, lu);
        System.out.println("Mot envoyé par le serveur est : " + serverResponse);
        sockOut.close();
        sockIn.close();
        sock.close();
    }

}
