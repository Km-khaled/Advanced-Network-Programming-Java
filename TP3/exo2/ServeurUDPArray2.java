package exo2;

import java.io.*;
import java.net.*;

public class ServeurUDPArray2 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(3000);
        System.out.println("Éléments du tableau reçus :");

        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            int num = dataInputStream.readInt();
            System.out.println(num);
        }
    }
}
