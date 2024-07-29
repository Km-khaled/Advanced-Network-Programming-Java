package exo1;

import java.io.*;

import java.net.*;

public class Emetteur {

    public static void main(String[] args) throws IOException {

        //Address
        final int multiCastPort = 4000;

        //Create Socket
        InetAddress group = InetAddress.getByName("224.0.0.1");
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);

        //Prepare Data
        Entreprise entreprise = new Entreprise(10, "SOGERHWIT");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entreprise);
        byte[] data = baos.toByteArray();

        //Send data
        s.send(new DatagramPacket(data, data.length, group, multiCastPort));
        System.out.println("Fin emission");

    }
}
