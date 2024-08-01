package exo1_version_1;

import java.net.*;
import java.io.*;
import java.util.HashMap;

public class Serveur {

    private static HashMap<String, Integer> symptomMap;

    public static void main(String[] args) {
        try {
            symptomMap = A.loadSymptomMap();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Serveur serveur = new Serveur();
        System.out.println("Server waiting for connection...");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("Client connected");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                String symptom;
                while ((symptom = sockIn.readUTF()) != null) {
                    int count = symptomMap.getOrDefault(symptom, 0);
                    sockOut.writeInt(count);
                    sockOut.flush();
                }
                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
