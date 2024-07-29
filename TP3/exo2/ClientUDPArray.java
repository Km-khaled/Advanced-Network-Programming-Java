package exo2;

import java.io.*;
import java.net.*;

public class ClientUDPArray {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");
        int[] tab = {1, 6, 8, 9, 13, 10};

        // Écrire le tableau dans un ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(tab);
        objectOutputStream.flush();

        // Convertir le ByteArrayOutputStream en un tableau d'octets
        byte[] buffer = byteArrayOutputStream.toByteArray();

        // Envoyer le tableau d'octets dans un DatagramPacket
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 9000);
        socket.send(packet);

        System.out.println("Client a envoyé le tableau d'entiers au serveur");
    }
}
