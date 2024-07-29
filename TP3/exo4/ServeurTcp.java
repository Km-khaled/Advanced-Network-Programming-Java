package exo4;

import java.io.*;
import java.net.*;

public class ServeurTcp {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            String request = in.readLine();
            String[] parts = request.split(" ");

            if (parts.length != 3) {
                String errorResponse = "Error: Invalid Format";
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(errorResponse);
                continue;
            }

            String operation = parts[0];
            double operand1 = Double.parseDouble(parts[1]);
            double operand2 = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operation) {
                case "ADD":
                    result = operand1 + operand2;
                    break;
                case "MUL":
                    result = operand1 * operand2;
                    break;
                case "SUB":
                    result = operand1 - operand2;
                    break;
                case "DIV":
                    result = operand1 / operand2;
                    break;
                case "POW":
                    result = Math.pow(operand1, operand2);
                    break;
                default:
                    String errorResponse = "Error: Unknown Operation";
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(errorResponse);
                    continue;
            }

            String response = String.valueOf(result);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(response);

            socket.close();
        }
    }
}
