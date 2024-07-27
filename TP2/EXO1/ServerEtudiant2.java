package EXO1;

import java.net.*;
import java.io.*;


public class ServerEtudiant2 {
    public static void main(String args[]) {
        Etudiant[] tabEtudiant = {
                new Etudiant("A", "GL", 13), new Etudiant("B", "RSD", 12), new Etudiant("C", "SIC", 14),
                new Etudiant("D", "MID", 15), new Etudiant("E", "RSD", 16), new Etudiant("F", "SIC", 17),
                new Etudiant("G", "GL", 18), new Etudiant("H", "MID", 19), new Etudiant("I", "RSD", 20),
                new Etudiant("J", "SIC", 12), new Etudiant("K", "GL", 11), new Etudiant("L", "MID", 10)
        };
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecté");

                ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                String specialization = sockIn.readLine();

                Etudiant bestStudent = findBestStudent(tabEtudiant, specialization);

                sockOut.writeObject(bestStudent);

                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {}
        }
    }

    private static Etudiant findBestStudent(Etudiant[] students, String specialization) {
        Etudiant bestStudent = null;
        int maxAverage = 0;

        for (Etudiant student : students) {
            if (student.getSpecialite().equals(specialization) && student.getMoy() > maxAverage) {
                maxAverage = student.getMoy();
                bestStudent = student;
            }
        }

        return bestStudent;
    }
}