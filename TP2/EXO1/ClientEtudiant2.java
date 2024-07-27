
package EXO1;

import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class ClientEtudiant2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String hostName = "localhost";
        
Scanner scanner = new Scanner(System.in);
System.out.print("Entrez la specialité recherchée : ");
       String specialite = scanner.next();
        
        
        

        Socket sock = null;
        PrintWriter sockOut = null;
        ObjectInputStream sockIn = null;

        try {
            sock = new Socket(hostName, 7777);
            sockOut = new PrintWriter(sock.getOutputStream(), true);
            sockIn = new ObjectInputStream(sock.getInputStream());

            // Send the specialization to the server
            sockOut.println(specialite);

            // Receive the best student from the server
            Object recu = sockIn.readObject();

            if (recu instanceof Etudiant) {
                Etudiant bestStudent = (Etudiant) recu;
                System.out.println("Best student in specialization " + specialite + ": " + bestStudent);
            } else {
                System.out.println("Server response unrecognized.");
            }

        } catch (UnknownHostException e) {
            System.err.println("Host non atteignable : " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connection impossible avec : " + hostName);
            System.exit(1);
        } finally {
            if (sockOut != null) sockOut.close();
            if (sockIn != null) sockIn.close();
            if (sock != null) sock.close();
        }
    }
}