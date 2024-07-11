package EXO2;

import EXO2.Personne;
import java.io.*;
import java.util.ArrayList;

public class EcrirePersonne {
public static void main(String[] args) {


ArrayList<Personne> liste=new ArrayList<Personne>();
liste.add(new Personne("prsn1","mourad",21));
liste.add(new Personne("prsn2","mohammed",20));
liste.add(new Personne("prsn3","yahia",30));


try { FileOutputStream fileOut = new FileOutputStream("./src/EXO2/personne.txt");

ObjectOutputStream out = new ObjectOutputStream(fileOut);

out.writeObject(liste); 

out.close();

fileOut.close();

System.out.printf("Ecrire dans ./personne.txt ");

} catch (IOException i) {i.printStackTrace();}

}
}