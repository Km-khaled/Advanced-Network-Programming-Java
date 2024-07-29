package exo2;

import java.io.*;
import java.net.*;

public class ClientUDPArray2 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");
        int[] tab = {1, 6, 8, 9, 13, 10};

        for (int num : tab) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(num);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 3000);
            socket.send(packet);
        }

        System.out.println("Client a envoyé tous les éléments du tableau au serveur");
    }
}
