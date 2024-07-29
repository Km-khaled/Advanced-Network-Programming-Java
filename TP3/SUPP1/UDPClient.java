package SUPP1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            ArrayList<String> k = new ArrayList<>();

            DatagramPacket sendPacket;
            DatagramPacket receivePacket;

            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("number: ");
                String numberStr = scanner.nextLine();
                if (numberStr.equals("exit")) {
                    break; // Exit the loop if "exit" is entered
                }

                k.add(numberStr);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(k);
            sendData = outputStream.toByteArray();

            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            System.out.println(k);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            int[] paireNumbers = (int[]) objectInputStream.readObject();
            int[] impaireNumbers = (int[]) objectInputStream.readObject();

            try (FileWriter file1 = new FileWriter("./src/SUPP1/paireNumbers.txt")) {
                for (int num : paireNumbers) {
                    if (num != 0) {
                        file1.write(num + "\n");
                    }
                }
            }

            try (FileWriter file2 = new FileWriter("./src/SUPP1/impaireNumbers.txt")) {
                for (int num : impaireNumbers) {
                    if (num != 0) {
                        file2.write(num + "\n");
                    }
                }
            }

            System.out.println("Files created successfully.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
