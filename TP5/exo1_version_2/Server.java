package exo1_version_2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static HashMap<String, List<String>> symptomMap;

    public static void main(String[] args) {
        try {
            symptomMap = A.loadSymptomMap();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Server waiting for connection...");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("Client connected");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());

                String nom;
                while ((nom = sockIn.readUTF()) != null) {
                    List<String> symptoms = symptomMap.getOrDefault(nom, Collections.emptyList());
                    sockOut.writeInt(symptoms.size());
                    for (String symptom : symptoms) {
                        sockOut.writeUTF(symptom);
                    }
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
