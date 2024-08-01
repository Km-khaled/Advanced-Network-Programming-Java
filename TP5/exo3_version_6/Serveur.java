package exo3_version_6;

import java.io.*;
import java.net.*;
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

                    if (authInfo.containsKey(login) && authInfo.get(login).equals(password)) {

                        String type = in.readUTF();

                        if (type.equals("image")) {
                            sendImage(clientSocket);
                        }

                        if (type.equals("txt")) {
                            sendFile(clientSocket);
                        }

                    } else {
                        // Authentification échouée, fermez la connexion

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

    private static void sendImage(Socket clientSocket) throws IOException {
        File imageFile = new File("./src/exo3_version_6/resultat.jpg");
        byte[] buffer = new byte[4096];

        try (FileInputStream fis = new FileInputStream(imageFile); OutputStream os = clientSocket.getOutputStream()) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }

    private static void sendFile(Socket clientSocket) throws IOException {
        File fileToSend = new File("./src/exo3_version_6/received_file.txt");
        byte[] buffer = new byte[4096];

        try (FileInputStream fis = new FileInputStream(fileToSend); OutputStream os = clientSocket.getOutputStream()) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
