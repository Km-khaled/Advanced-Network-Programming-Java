package exo3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServeurUDP2 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9000);
        byte[] buffer = new byte[1024];

        // Créer une ArrayList de joueurs
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(9, "BENZEMA", "REAL"));
        joueurs.add(new Joueur(10, "MODRIC", "REAL"));
        joueurs.add(new Joueur(26, "MAHREZ", "City"));
        joueurs.add(new Joueur(30, "MESSI", "PSG"));
        joueurs.add(new Joueur(10, "NEYMAR", "PSG"));

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Récupérer le caractère envoyé par le client
            char caractere = new String(packet.getData(), 0, packet.getLength()).trim().charAt(0);

            // Filtrer les joueurs par caractère
            ArrayList<Joueur> joueursCaractere = new ArrayList<>();
            for (Joueur joueur : joueurs) {
                if (joueur.getNom().startsWith(String.valueOf(caractere))) {
                    joueursCaractere.add(joueur);
                }
            }

            // Envoyer la liste des joueurs commençant par le caractère au client
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(joueursCaractere);
            objectOutputStream.flush();

            byte[] data = byteArrayOutputStream.toByteArray();
            DatagramPacket reponse = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(reponse);
        }
    }
}
