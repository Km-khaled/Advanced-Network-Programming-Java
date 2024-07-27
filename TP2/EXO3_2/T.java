package EXO3_2;

import java.io.*;
import java.net.*;

public class T extends Thread {

    private Socket socket;
    OutputStream sockOut = null;
    InputStream sockIn = null;

    public T(Socket s) {
        socket = s;
    }

    public void run() {

        try {
            sockOut = socket.getOutputStream();
            sockIn = socket.getInputStream();
            byte[] buffer1 = new byte[1024];
            int lu;
            lu = sockIn.read(buffer1);
            String messageClient = new String(buffer1, 0, lu);
            String[] mots = messageClient.split(" ");
            StringBuilder response = new StringBuilder();
            for (String mot : mots) {
                if (!mot.isEmpty()) {
                    response.append(mot.charAt(mot.length() - 1));
                }
            }

            System.out.println("Mot envoyer par le client est : " + new String(buffer1));
            System.out.println("Nombre d'octets lu : " + lu);
            byte[] buffer2 = response.toString().getBytes();
            sockOut.write(buffer2);
            sockOut.flush();
            sockOut.close();
            sockIn.close();
            socket.close();
        } catch (IOException e) {
        }

    }
}
