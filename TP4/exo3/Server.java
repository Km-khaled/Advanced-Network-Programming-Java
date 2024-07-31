package exo3;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);

        while (true) {
            Socket socket = server.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String requete = "";
            while ((requete = dis.readUTF()) != null) {
                // separation l'operation et  les operandes
                String[] parts = requete.split(" ");
                String operation = parts[0];
                double operande1 = Double.parseDouble(parts[1]);
                double operande2 = Double.parseDouble(parts[2]);

                double resultat;
                switch (operation) {
                    case "ADD":
                        resultat = operande1 + operande2;
                        break;
                    case "MUL":
                        resultat = operande1 * operande2;
                        break;
                    case "SOUS":
                        resultat = operande1 - operande2;
                        break;
                    case "DIV":
                        resultat = operande1 / operande2;
                        break;
                    case "PUIS":
                        resultat = Math.pow(operande1, operande2);
                        break;
                    default:
                        resultat = Double.NaN;
                }

                // Envoyer le résultat au client
                dos.writeUTF(String.valueOf(resultat));
                dos.flush();
            }
        }
    }
}
