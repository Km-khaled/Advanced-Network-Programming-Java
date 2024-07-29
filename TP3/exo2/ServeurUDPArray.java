package exo2;

import java.io.*;
import java.net.*;

public class ServeurUDPArray {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9000);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        // Convertir les données reçues en un objet de type int[]
        byte[] data = packet.getData();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        int[] tab = (int[]) objectInputStream.readObject();

        // Afficher le contenu du tableau
        System.out.println("Tableau d'entiers reçu :");
        for (int num : tab) {
            System.out.println(num);
        }
    }
}
