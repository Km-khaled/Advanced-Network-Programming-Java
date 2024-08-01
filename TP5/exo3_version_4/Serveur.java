package exo3_version_4;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class Serveur {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                        String type = in.readUTF();
                        if (type.equals("image")) {
                            BufferedImage bimg = ImageIO.read(clientSocket.getInputStream());
                            ImageIO.write(bimg, "JPG", new File("./src/exo3_version_4/resultat.jpg"));
                            System.out.println("Image reçue et sauvegardée dans resultat.jpg");
                        } else if (type.equals("txt")) {
                            ABC.mystere(clientSocket.getInputStream(), new FileOutputStream("./src/exo3_version_4/received_file.txt"));
                            System.out.println("Fichier texte reçu et sauvegardé dans received_file.txt");
                        }

                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            clientThread.start();
        }
    }
}
