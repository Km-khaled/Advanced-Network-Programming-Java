package EXO3;



    import java.io.*;
    import java.net.*;

public class RSD {
public static void main(String[ ] args) throws IOException {
String host = "localhost"; 
InetAddress ip = InetAddress.getByName(host);
int debut = (int) System.currentTimeMillis();
boolean var = ip.isReachable(5000);
if (var) System.out.println("OK dans "+ ((int) System.currentTimeMillis() - debut)+" ms");
else System.out.println("Not OK");
}
}
