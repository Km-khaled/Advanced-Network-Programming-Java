package exo1;

import java.io.*;

import java.net.*;

public class UDPSocketServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(9000);

        byte[] buffer = new byte[1024];

        DatagramPacket recu = new DatagramPacket(buffer, buffer.length);

        socket.receive(recu);

        byte[] data = recu.getData();

//ByteArrayInputStream classe fille de InputStream
        ObjectInputStream a = new ObjectInputStream(new ByteArrayInputStream(data));

        Entreprise entreprise = (Entreprise) a.readObject();

        System.out.println("L'objet entreprise reçu : " + entreprise);

        InetAddress IPAddress = recu.getAddress();

        int port = recu.getPort();

        String reponse = "J'ai bien reçu l'objet";

        byte[] d = reponse.getBytes();

        DatagramPacket envoyer = new DatagramPacket(d, d.length, IPAddress, port);

        socket.send(envoyer);

    }

}
