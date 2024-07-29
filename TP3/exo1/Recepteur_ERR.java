package exo1;

import java.io.*;

import java.net.*;

public class Recepteur_ERR {

    public static void main(String argv[]) throws IOException {

        InetAddress group = InetAddress.getByName("200.0.0.0");

        MulticastSocket socket = new MulticastSocket(1000);

        socket.joinGroup(group);

        byte[] buf = new byte[1024];

        DatagramPacket recv = new DatagramPacket(buf, buf.length);

        System.out.println("En attente de reception ...");

        socket.receive(recv);

        String ch = new String(recv.getData());

        System.out.println("Debut reception\n " + ch + " \nFin reception");

    }
}
