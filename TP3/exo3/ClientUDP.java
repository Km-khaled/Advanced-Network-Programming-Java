package exo3;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        // Envoyer le nom de l'équipe au serveur
        String nomEquipe = "REAL";
        byte[] buffer = nomEquipe.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 9000);
        socket.send(packet);

        // Recevoir la liste des joueurs de l'équipe
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ArrayList<Joueur> joueursEquipe = (ArrayList<Joueur>) objectInputStream.readObject();

        System.out.println("Joueurs de l'équipe " + nomEquipe + " :");
        System.out.println(joueursEquipe);
    }
}
