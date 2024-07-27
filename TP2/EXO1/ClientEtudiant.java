
package EXO1;

import java.io.*; 
import java.net.*;
import java.util.Scanner;



//1

//public class ClientEtudiant {
//  public static void main(String[] args) throws IOException, ClassNotFoundException {
// String hostName = "localhost"; 
//String NomEtudiant = "A";  
//    Socket sock = null;
//    PrintWriter sockOut = null;
//    ObjectInputStream sockIn = null;
//    
//    
//    
//    try {
//      
////   sock = new Socket(); 
////  SocketAddress localaddr = new InetSocketAddress (8888);
////  SocketAddress serveur = new InetSocketAddress ("localhost", 7777); 
////  sock.bind (localaddr);   
////  sock.connect (serveur);  
//
//
//            sock = new Socket(hostName, 7777);
//
//      sockOut = new PrintWriter(sock.getOutputStream(), true);
//       sockIn = new ObjectInputStream(sock.getInputStream());
//
//            System.out.println("Taille de la zone tampon d'envoi côté client : " + sock.getSendBufferSize() + " octets");
//  
//       System.out.println("Taille de la zone tampon de réception côté client : " + sock.getReceiveBufferSize() + " octets");
//
//   // Affichage  numéro de port local
//      System.out.println("Numéro de port local : " + sock.getLocalPort());
//
//      // Affichage  numéro de port distant
//      System.out.println("Numéro de port distant : " + sock.getPort());
//      
//      
//
//            
//    }catch (UnknownHostException e) {System.err.println("host non atteignable : "+hostName);  System.exit(1); }
//       catch (IOException e) {System.err.println("connection impossible avec : "+hostName); System.exit(1);}
//        sockOut.println(NomEtudiant); // envoyer le nom au serveur
//    try {
//      Object recu = sockIn.readObject(); // récupérer l’objet Etudiant envoyé par le serveur
//      if (recu == null) System.out.println("");
//      else { Etudiant etudiant = (Etudiant)recu;
//        System.out.println("serveur -> client : " + etudiant);  
//      }
//    } catch (ClassNotFoundException e) {System.err.println("Classe inconnue : "+hostName); System.exit(1);}  
//     sockOut.close();
//    sockIn.close();
//    sock.close();
//  }
//} 


// 2

public class ClientEtudiant {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String hostName = "localhost";
//        String NomEtudiant = "A";
        Socket sock = null;
        PrintWriter sockOut = null;
        ObjectInputStream sockIn = null;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez la moyenne recherchée : ");
        int moyenne = scanner.nextInt();

        try {
            sock = new Socket(hostName, 7777);
            
 
            
            sockOut = new PrintWriter(sock.getOutputStream(), true);
            sockIn = new ObjectInputStream(sock.getInputStream());

            // Envoi de l'entier représentant la moyenne au serveur
            sockOut.println(moyenne);

            // Réception du tableau d'étudiants ayant une moyenne supérieure à l'entier envoyé
            Object recu = sockIn.readObject(); // get the array of students with average greater than the integer
            if (recu == null)
                System.out.println("Connection error");
            else {
                Etudiant[] students = (Etudiant[]) recu;
                for (Etudiant student : students) {
                    System.out.println("Student with average > " + moyenne + ": " + student);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Host non atteignable : " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connection impossible avec : " + hostName);
            System.exit(1);
        } finally {
            // Close resources in the finally block
            if (sockOut != null) sockOut.close();
            if (sockIn != null) sockIn.close();
            if (sock != null) sock.close();
            scanner.close();
        }
    }
}







