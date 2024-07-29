package SUPP1;

import java.io.*;
import java.net.*;

import java.util.ArrayList;

public class UDPServer {

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Waiting for data from client...");
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                ArrayList<String> numbers = (ArrayList<String>) objectInputStream.readObject();

                ArrayList<Integer> evenNumbers = new ArrayList<>();
                ArrayList<Integer> oddNumbers = new ArrayList<>();

                for (String number : numbers) {
                    int num = Integer.parseInt(number);
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }

                int[] evenArray = evenNumbers.stream().mapToInt(i -> i).toArray();
                int[] oddArray = oddNumbers.stream().mapToInt(i -> i).toArray();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(evenArray);
                objectOutputStream.writeObject(oddArray);
                sendData = outputStream.toByteArray();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Data sent to client.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
