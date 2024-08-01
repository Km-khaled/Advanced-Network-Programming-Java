package exo3_version_2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 9001);

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Voulez-vous envoyer une image ou un fichier texte ? Tapez 'image' ou 'txt'");

        String type = br.readLine();

        out.writeUTF(type);

        if (type.equals("image")) {

            BufferedImage bimg = ImageIO.read(new File("./src/ex3_console/univ.jpg"));

            ImageIO.write(bimg, "JPG", socket.getOutputStream());

            System.out.println("Image envoyée");

        } else if (type.equals("txt")) {

            ABC.mystere(new FileInputStream("./src/ex3_console/test1.txt"), socket.getOutputStream());

            System.out.println("Fichier texte envoyé");

        }
        out.close();
        socket.close();

    }

}
