package exo3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServeurUDP {

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

            // Récupérer le nom de l'équipe envoyé par le client
            String nomEquipe = new String(packet.getData(), 0, packet.getLength()).trim();

            // Filtrer les joueurs par équipe
            ArrayList<Joueur> joueursEquipe = new ArrayList<>();
            for (Joueur joueur : joueurs) {
                if (joueur.getEquipe().equals(nomEquipe)) {
                    joueursEquipe.add(joueur);
                }
            }

            // Envoyer la liste des joueurs de l'équipe au client
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(joueursEquipe);
            objectOutputStream.flush();

            byte[] data = byteArrayOutputStream.toByteArray();
            DatagramPacket reponse = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(reponse);
        }
    }
}
