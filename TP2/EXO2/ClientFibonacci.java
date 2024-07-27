package EXO2;



import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientFibonacci {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7777);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

     
            System.out.println("Entrez le numéro pour calculer son suite de Fibonacci :");

            Scanner input= new Scanner(System.in);
            int nbr=input.nextInt();

            out.writeInt(nbr);
            
            System.out.println("Les nombres de Fibonacci sont :");
            for (int i = 0; i <= nbr; i++) {
                long fibNumber = in.readLong();
                System.out.print(fibNumber + " ");
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
