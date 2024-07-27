package ExO3;

import java.net.*;
import java.io.*;

public class ServerEcho {

    public static void main(String args[]) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("Connecté");
                OutputStream sockOut = sock.getOutputStream();
                InputStream sockIn = sock.getInputStream();

                byte[] buffer1 = new byte[1024];
                int lu;
                try {
                    lu = sockIn.read(buffer1);
                    System.out.println("Phrase envoyée par le client : " + new String(buffer1));
                    System.out.println("Nombre d'octets lus : " + lu);

                    String phrase = new String(buffer1).trim();
                    String[] mots = phrase.split("\\s+"); // Diviser la phrase en mots

                    StringBuilder message = new StringBuilder();
                    for (String mot : mots) {
                        message.append(mot.charAt(mot.length() - 1)); // Ajouter le dernier caractère de chaque mot
                    }

                    byte[] buffer2 = message.toString().getBytes(); // Convertir le message en tableau de bytes
                    sockOut.write(buffer2);
                    sockOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sockOut.close();
                sockIn.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
