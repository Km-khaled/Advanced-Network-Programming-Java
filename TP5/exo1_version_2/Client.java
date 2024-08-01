package exo1_version_2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {

    private Socket sock = null;
    private DataOutputStream sockOut = null;
    private DataInputStream sockIn = null;

    private JButton jbtGetMoy = new JButton("Afficher les Symptômes");
    private JTextField jtfNom = new JTextField();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> symptomList = new JList<>(listModel);

    public Client() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2));
        panneau.add(new JLabel("Patient"));
        panneau.add(jtfNom);
        panneau.add(new JLabel("Symptômes"));
        panneau.add(symptomList);
        add(panneau, BorderLayout.CENTER);
        add(jbtGetMoy, BorderLayout.SOUTH);
        jbtGetMoy.addActionListener(this);
    }

    public void init() {
        try {
            sock = new Socket("localhost", 7777);
            sockOut = new DataOutputStream(sock.getOutputStream());
            sockIn = new DataInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.clear();
        try {
            sockOut.writeUTF(jtfNom.getText().trim());
            sockOut.flush();
            int symptomCount = sockIn.readInt();
            List<String> symptoms;
            if (symptomCount == 0) {
                JOptionPane.showMessageDialog(this, "Patient not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            symptoms = new ArrayList<>(symptomCount);
            for (int i = 0; i < symptomCount; i++) {
                symptoms.add(sockIn.readUTF());
            }
            // Concatenate symptoms into one string separated by spaces
            StringBuilder concatenatedSymptoms = new StringBuilder();
            for (String symptom : symptoms) {
                concatenatedSymptoms.append(symptom).append(" ");
            }
            listModel.addElement(concatenatedSymptoms.toString().trim()); // Add the concatenated string to the list model
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client a = new Client();
        a.setTitle("Traitement des symptômes");
        a.setSize(500, 200);
        a.init();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }
}
