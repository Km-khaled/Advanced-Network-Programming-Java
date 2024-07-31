package sol_exo1;

import java.net.*;
import java.io.*;
import java.util.HashMap;

class Serveur {

    private HashMap<String, Double> table;

    public Serveur() {
        table = new HashMap<>();
        table.put("badr", 13.5);
        table.put("saleh", 15.5);
        table.put("youcef", 9.5);
        table.put("anes", 8.5);
    }

    public int TrouverMoy(String name) {
        if (table.containsKey(name)) {
            double moyenne = table.get(name);
            if (moyenne >= 10) {
                return 1; // Moyenne > 10
            } else {
                return 2; // Moyenne < 10
            }
        } else {
            return 3; // Étudiant non trouvé
        }
    }

    public static void main(String args[]) {
        Serveur b = new Serveur();
        System.out.println("Serveur en attente de connexion ...");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                String name;
                while ((name = sockIn.readUTF()) != null) {
                    sockOut.writeInt(b.TrouverMoy(name));
                    sockOut.flush();
                }
                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        }
    }
}
