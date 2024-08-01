package exo3_version_5;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Serveur {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);

        // Créez une structure de données pour stocker les informations d'authentification
        Map<String, String> authInfo = new HashMap<>();
        authInfo.put("user1", "password1");
        authInfo.put("user2", "password2");
        // Ajoutez d'autres utilisateurs si nécessaire

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread clientThread = new Thread(() -> {
                try {
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                    String login = in.readUTF();
                    String password = in.readUTF();

                    // Vérifiez les informations d'authentification
                    if (authInfo.containsKey(login) && authInfo.get(login).equals(password)) {
                        // Authentification réussie, traitez les données
                        String type = in.readUTF();
                        if (type.equals("image")) {
                            BufferedImage bimg = ImageIO.read(clientSocket.getInputStream());
                            ImageIO.write(bimg, "JPG", new File("./src/exo3_version_5/resultat.jpg"));
                            System.out.println("Image reçue et sauvegardée dans resultat.jpg");
                        } else if (type.equals("txt")) {
                            ABC.mystere(clientSocket.getInputStream(), new FileOutputStream("./src/exo3_version_5/received_file.txt"));
                            System.out.println("Fichier texte reçu et sauvegardé dans received_file.txt");
                        }
                    } else {
                        // Authentification échouée, fermez la connexion
//                       DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
//                            out.writeBoolean(false); // Envoyer une indication d'échec d'authentification

                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                        out.writeUTF("Authentification échouée: nom d'utilisateur ou mot de passe incorrect.");
                        out.flush();
                        System.out.println("Authentification échouée pour " + login);
                    }

                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            clientThread.start();
        }
    }
}
