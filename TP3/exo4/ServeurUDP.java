package exo4;

import java.net.*;

public class ServeurUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9000);
        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String requete = new String(packet.getData(), 0, packet.getLength()).trim();
            String[] parties = requete.split(" ");

            if (parties.length != 3) {
                String reponse = "Erreur: Format invalide";
                byte[] data = reponse.getBytes();
                DatagramPacket reponsePacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                socket.send(reponsePacket);
                continue;
            }

            String operation = parties[0];
            double operande1 = Double.parseDouble(parties[1]);
            double operande2 = Double.parseDouble(parties[2]);
            double resultat = 0;

            switch (operation) {
                case "ADD":
                    resultat = operande1 + operande2;
                    break;
                case "MUL":
                    resultat = operande1 * operande2;
                    break;
                case "SOUS":
                    resultat = operande1 - operande2;
                    break;
                case "DIV":
                    resultat = operande1 / operande2;
                    break;
                case "PUIS":
                    resultat = Math.pow(operande1, operande2);
                    break;
                default:
                    String reponse = "Erreur: Opération inconnue";
                    byte[] data = reponse.getBytes();
                    DatagramPacket reponsePacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                    socket.send(reponsePacket);
                    continue;
            }

            String reponse = String.valueOf(resultat);
            byte[] data = reponse.getBytes();
            DatagramPacket reponsePacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(reponsePacket);
        }
    }
}
