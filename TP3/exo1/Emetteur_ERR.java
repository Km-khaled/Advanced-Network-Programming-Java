package exo1;

import java.io.*;

import java.net.*;

public class Emetteur_ERR {

    public static void main(String argv[]) throws IOException {

        String msg = "JE SUIS UN ETUDIANT INFORMATIQUE";

        InetAddress group = InetAddress.getByName("200.0.0.0");

        DatagramSocket socket = new DatagramSocket();

        DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(), group, 1000);

        socket.send(hi);

        System.out.println("Fin emission");

    }

}
