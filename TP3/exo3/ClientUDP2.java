package exo3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientUDP2 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        // Envoyer le caractère au serveur
        char caractere = 'B';
        byte[] buffer = new byte[1];
        buffer[0] = (byte) caractere;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 9000);
        socket.send(packet);

        // Recevoir la liste des joueurs commençant par le caractère
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ArrayList<Joueur> joueursCaractere = (ArrayList<Joueur>) objectInputStream.readObject();

        System.out.print("Joueurs commençant par '" + caractere + "' : [");
        for (int i = 0; i < joueursCaractere.size(); i++) {
            Joueur joueur = joueursCaractere.get(i);
            System.out.print("numero = " + joueur.getNumero() + " nom = " + joueur.getNom() + " equipe = " + joueur.getEquipe());
            if (i != joueursCaractere.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
