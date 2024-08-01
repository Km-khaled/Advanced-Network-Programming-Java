package exo2;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ServerImage {

    public static void main(String[] args) {
        try {
            Socket server = new ServerSocket(6066).accept();
            System.out.println("Client connected.");

            BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));

            if (img != null) {
                // Get the directory of the ServerImage class
                File classDir = new File(ServerImage.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();

                // Specify the file path relative to the src directory
                File outputFile = new File(classDir.getParentFile(), "src/exo2/test.jpg");

                if (ImageIO.write(img, "JPG", outputFile)) {
                    System.out.println("Image received and saved as " + outputFile.getAbsolutePath());
                } else {
                    System.err.println("Failed to save the received image.");
                }
            } else {
                System.err.println("Failed to read the image from the input stream.");
            }

            server.close();
        } catch (IOException | URISyntaxException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
