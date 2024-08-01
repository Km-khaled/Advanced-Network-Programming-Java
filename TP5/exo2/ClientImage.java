package exo2;

import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ClientImage {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 6066);

            // Read the image as a stream from the classpath
            InputStream inputStream = ClientImage.class.getResourceAsStream("univ.JPG");
            if (inputStream != null) {
                BufferedImage bimg = ImageIO.read(inputStream);

                if (bimg != null) {
                    ImageIO.write(bimg, "JPG", client.getOutputStream());
                    System.out.println("Image sent to server.");
                } else {
                    System.err.println("Failed to read the image.");
                }
            } else {
                System.err.println("Failed to locate the image file.");
            }

            client.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
