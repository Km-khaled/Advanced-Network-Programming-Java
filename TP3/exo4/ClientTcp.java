package exo4;

import java.io.*;
import java.net.Socket;

public class ClientTcp {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9000);

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);

        String request = "DIV 12 7";
        out.println(request);

        InputStream inputStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String response = in.readLine();
        System.out.println("Server Response: " + response);

        socket.close();
    }
}
