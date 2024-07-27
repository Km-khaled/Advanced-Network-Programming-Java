package ExO3;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class ClientEcho {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer votre phrase : ");
        String phrase = scanner.nextLine();

        Socket sock = null;
        OutputStream sockOut = null;
        InputStream sockIn = null;

        try {
            sock = new Socket("localhost", 7777);
            sockOut = sock.getOutputStream();
            sockIn = sock.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("Hôte non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connexion impossible avec : localhost");
            System.exit(1);
        }

        byte[] buffer1 = phrase.getBytes();

        try {
            sockOut.write(buffer1);
            sockOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] buffer2 = new byte[1024];
        int lu = sockIn.read(buffer2);
        System.out.println("Phrase envoyée par le serveur : " + new String(buffer2));
        System.out.println("Nombre d'octets lus : " + lu);

        sockOut.close();
        sockIn.close();
        sock.close();
        scanner.close();
    }
}
