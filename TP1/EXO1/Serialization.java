package EXO1;

import java.io.*;

public class Serialization {
public static void main(String[] args) {

Employee emp = new Employee();

emp.nom = "BENMAMMAR";

emp.prenom = "BADR";

//emp.adresse = "TLEMCEN";

emp.affiliation = "UABT";

try { FileOutputStream fileOut = new FileOutputStream("./src/EXO1/employee.txt");

ObjectOutputStream out = new ObjectOutputStream(fileOut);

out.writeObject(emp); /* Sauvegarder « emp » dans le fichier « employee » sous forme binaire (suite d’octets) */


out.close();

fileOut.close();

System.out.printf("Serialisation dans ./employee.txt ");

} catch (IOException i) {i.printStackTrace();}

}
}