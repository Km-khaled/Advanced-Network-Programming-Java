package exo4;

import java.net.*;

public class ClientUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        String requete = "DIV 12 5";
        byte[] buffer = requete.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 9000);
        socket.send(packet);

        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String reponse = new String(packet.getData(), 0, packet.getLength()).trim();
        System.out.println("Réponse du serveur: " + reponse);

        socket.close();
    }
}
