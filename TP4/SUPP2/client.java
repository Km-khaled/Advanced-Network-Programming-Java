package SUPP2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class client extends JFrame implements ActionListener {

    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    private JButton searchButton = new JButton("Search");
    private JTextField birthYearField = new JTextField();
    private JTextField resultField = new JTextField();

    public client() {
        setTitle("Employee Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Birth Year:"));
        panel.add(birthYearField);
        panel.add(new JLabel("Matching Employees:"));
        panel.add(resultField);
        resultField.setEditable(false);
        panel.add(new JLabel());
        panel.add(searchButton);

        add(panel, BorderLayout.CENTER);

        searchButton.addActionListener(this);
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 7777);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Host not found: localhost");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error occurred.");
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String birthYearStr = birthYearField.getText().trim();

        if (!birthYearStr.isEmpty()) {
            int birthYear = Integer.parseInt(birthYearStr);

            connectToServer();

            try {
                out.writeInt(birthYear);
                out.flush();

                int count = in.readInt();
                resultField.setText(String.valueOf(count));
            } catch (IOException ex) {
                resultField.setText("Error reading server response: " + ex.getMessage());
            } finally {
                closeConnection();
            }
        }
    }

    public static void main(String[] args) {
        client client = new client();
        client.setVisible(true);
    }
}
