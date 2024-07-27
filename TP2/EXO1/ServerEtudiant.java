
package EXO1;

import java.net.*;
import java.io.*;



public class ServerEtudiant {
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
                System.out.println("connecte");

                ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                int moyenneRecherchee = Integer.parseInt(sockIn.readLine());

                Etudiant[] etudiantsFiltres = filterStudents(tabEtudiant, moyenneRecherchee);

                // Send the filtered students to the client
                sockOut.writeObject(etudiantsFiltres);

                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {}
        }
    }

    private static Etudiant[] filterStudents(Etudiant[] students, int average) {
        // Count the number of students with an average greater than the given average
        int count = 0;
        for (Etudiant student : students) {
            if (student.getMoy() > average) {
                count++;
            }
        }

        // Create a new array to store the filtered students
        Etudiant[] filteredStudents = new Etudiant[count];
        int index = 0;

        // Fill the array with students with an average greater than the given average
        for (Etudiant student : students) {
            if (student.getMoy() > average) {
                filteredStudents[index++] = student;
            }
        }

        return filteredStudents;
    }
}

