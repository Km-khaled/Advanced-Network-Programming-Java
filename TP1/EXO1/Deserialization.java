package EXO1;

import java.io.*;
public class Deserialization {
    
    public static void main(String[] args) {

Employee emp=null;

try { 
    
    FileInputStream fileIn = new FileInputStream("./src/EXO1/employee.txt");

ObjectInputStream in = new ObjectInputStream(fileIn);

emp = (Employee) in.readObject();

in.close();
fileIn.close();

} 

    catch (IOException i) {
        i.printStackTrace();

    } 

catch (ClassNotFoundException c) {
    c.printStackTrace();
}

System.out.println("Deserialisation...");

System.out.println("Nom: " + emp.nom);

System.out.println("Prenom: " + emp.prenom);

System.out.println("Adresse: "+emp.adresse);

System.out.println("Affiliation: "+emp.affiliation);

}
}
