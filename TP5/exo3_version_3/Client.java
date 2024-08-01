package exo3_version_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {

    private Socket sock = null;
    private DataOutputStream sockOut = null;
    private DataInputStream sockIn = null;
    private JButton jbtEnvoyer = new JButton("Envoyer");
    private JComboBox<String> jcbTypeDonnee = new JComboBox<>(new String[]{"image", "txt"});
    private JTextField jtfCheminFichier = new JTextField();

    public Client() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2));
        panneau.add(new JLabel("Type de donnée: "));
        panneau.add(jcbTypeDonnee);
        panneau.add(new JLabel("Chemin du Fichier: "));
        panneau.add(jtfCheminFichier);
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
        String type = (String) jcbTypeDonnee.getSelectedItem();
        String cheminFichier = jtfCheminFichier.getText().trim();

        try {
            sockOut.writeUTF(type);
            sockOut.flush();

            if (type.equals("image")) {

                ABC.mystere(new FileInputStream(cheminFichier), sock.getOutputStream());

                // Flush the output stream to ensure all data is sent
                System.out.println("Image envoyée");
            } else if (type.equals("txt")) {
                ABC.mystere(new FileInputStream(cheminFichier), sock.getOutputStream());
                System.out.println("Fichier texte envoyé");

            }

            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
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