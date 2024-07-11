package EXO2;
import EXO2.Personne;
import java.io.*;
import java.util.ArrayList;

public class LirePersonne {
    
    
        public static void main(String[] args) {


try { 
    
    FileInputStream fileIn = new FileInputStream("./src/EXO2/personne.txt");

ObjectInputStream in = new ObjectInputStream(fileIn);

ArrayList liste = (ArrayList) in.readObject();


for (int i=0; i<liste.size(); i++) {
             Personne elem = (Personne)liste.get(i);          
             System.out.println(elem);
         }


in.close();
fileIn.close();

} 

    catch (IOException i) {
        i.printStackTrace();

    } 

catch (ClassNotFoundException c) {
    c.printStackTrace();
}

System.out.println("Lire...");


}
    
}
