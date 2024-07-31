package SUPP2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("Server started and listening on port 7777...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                int birthYear = in.readInt();

                int matchingEmployeesCount = 0;

                String file = "./src/SUPP/employees.txt";
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String firstName = tokenizer.nextToken();
                    String lastName = tokenizer.nextToken();
                    tokenizer.nextToken(); // Skip gender
                    LocalDate birthDate = LocalDate.parse(tokenizer.nextToken(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (birthDate.getYear() == birthYear) {
                        matchingEmployeesCount++;
                    }
                }

                reader.close();

                out.writeInt(matchingEmployeesCount);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
