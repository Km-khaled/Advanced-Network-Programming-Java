package exo1;

import java.io.*;

import java.net.*;

public class Recepteur {

    public static void main(String[] args) throws IOException {
        //Address
        final int multiCastPort = 4000;
        final int bufferSize = 1024 * 4; //Maximum size of transfer object

        InetAddress group = InetAddress.getByName("224.0.0.1");
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);

        //Receive data
        System.out.println("En attente de reception ...");
        //Create buffer
        byte[] buffer = new byte[bufferSize];
        s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));

        //Deserialze object
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        try {
            Object readObject = ois.readObject();
            if (readObject instanceof Entreprise) {
                Entreprise message = (Entreprise) readObject;
                System.out.println("L’objet entreprise recu : " + message);
            } else {
                System.out.println("The received object is not of type Entreprise!");
            }
        } catch (Exception e) {
            System.out.println("No object could be read from the received UDP datagram.");
        }

    }
}
