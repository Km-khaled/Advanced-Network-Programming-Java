package EXO3;

import java.net.*;

public class DD {

public static void main(String [ ] args) {


String host = "localhost"; 

mystere (host);

}

private static void mystere (String host) {

try { InetAddress b = InetAddress.getByName(host);
boolean var = b.isReachable(2000);

if (var) System.out.println("OK : " + host);

else System.out.println("Not OK : " + host);

} catch (Exception e) {e.printStackTrace();}

}

}