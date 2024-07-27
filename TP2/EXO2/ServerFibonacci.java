
package EXO2;

import java.io.*;
import java.net.*;

public class ServerFibonacci {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Serveur en attente de connexion...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté.");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                int n = in.readInt();

                long a = 0, b = 1;
                for (int i = 0; i <= n; i++) {
                    out.writeLong(a);
                    long temp = a;
                    a = b;
                    b = temp + b;
                }

                // Fermeture des flux et de la socket
                in.close();
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
