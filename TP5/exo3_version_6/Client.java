package exo3_version_6;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame implements ActionListener {

    private Socket sock = null;
    private DataOutputStream sockOut = null;
    private DataInputStream sockIn = null;
    private JButton jbtEnvoyer = new JButton("Envoyer");
    private JComboBox<String> jcbTypeDonnee = new JComboBox<>(new String[]{"image", "txt"});
    private JTextField jtfLogin = new JTextField();
    private JPasswordField jpfPassword = new JPasswordField();

    public Client() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(3, 2));

        panneau.add(new JLabel("Login: "));
        panneau.add(jtfLogin);
        panneau.add(new JLabel("Mot de passe: "));
        panneau.add(jpfPassword);

        panneau.add(new JLabel("Type de donnée: "));
        panneau.add(jcbTypeDonnee);
        add(panneau, BorderLayout.CENTER);
        add(jbtEnvoyer, BorderLayout.SOUTH);
        jbtEnvoyer.addActionListener(this);
    }

    public void init() {
        try {
            sock = new Socket("localhost", 9001);
            sockOut = new DataOutputStream(sock.getOutputStream());
            sockIn = new DataInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Hôte non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connexion impossible avec : localhost");
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String login = jtfLogin.getText().trim();
        String password = new String(jpfPassword.getPassword());
        String type = (String) jcbTypeDonnee.getSelectedItem();

        try {

            sockOut.writeUTF(login);
            sockOut.writeUTF(password);
            sockOut.flush();

            sockOut.writeUTF(type);
            sockOut.flush();

            if (type.equals("image")) {
                JFileChooser fileChooser = new JFileChooser();
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    receiveFile(sockIn, fileToSave);
                    System.out.println("Image reçue et sauvegardée à : " + fileToSave.getAbsolutePath());
                }
            }

            if (type.equals("txt")) {
                JFileChooser fileChooser = new JFileChooser();
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    receiveFile(sockIn, fileToSave);
                    System.out.println("Fichier texte reçu et sauvegardé à : " + fileToSave.getAbsolutePath());
                }
            }

            try {
                // Essayez de lire un éventuel message d'erreur du serveur
                String errorMessage = sockIn.readUTF();
                if (!errorMessage.isEmpty()) {
                    JOptionPane.showMessageDialog(null, errorMessage, "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                // Aucun message d'erreur reçu, ignorer
            }

            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void receiveFile(DataInputStream in, File fileToSave) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;

        try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setTitle("Client IHM");
        client.setSize(350, 150);
        client.init();
        client.setLocationRelativeTo(null);
        client.setVisible(true);
    }
}
