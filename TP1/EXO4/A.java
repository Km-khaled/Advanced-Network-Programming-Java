

package EXO4;

import java.io.*;
import java.util.*;


public class A {

public static void main(String args[]) throws IOException {

HashMap<String, Integer> map = new HashMap<>();

try (Scanner a = new Scanner(new File("./src//EXO4/symptomes.txt"))) {

while (a.hasNextLine()) ajouter(map, a.nextLine());

}

}

static void ajouter(Map<String, Integer> map, String symptome) {

// compute() prend en paramètre une clé (symptome dans ce cas),

// ainsi qu'une fonction à appliquer sur la clé et la valeur correspondante

// (la fonction lambda (key, value) -> (value == null) ? 1 : value + 1) dans ce cas)

map.compute(symptome, (key, value) -> (value == null) ? 1 : value + 1);
System.out.println(map);


}

}



//public class A {
//  static Integer var = 1;
//  public static void main(String args[]) throws IOException {
//    Map<String, Integer> map = new HashMap<>();   
//    Scanner a = new Scanner(new File("./src/TP1/symptomes.txt"));
//      while(a.hasNextLine()) traiter (a.nextLine(), map);
//    System.out.println(map);
//  }
//  static void traiter(String ligne, Map hash) {
//    StringTokenizer st = new StringTokenizer(ligne);
//    while (st.hasMoreTokens())  Ajouter (hash, st.nextToken());
//  }
//  static void Ajouter (Map map, String mot) {
//    Object k = map.get(mot); // le mot existe ou pas encore dans le map ?
//    if (k == null) map.put(mot,var); // insérer le mot pour la première fois
//     else { // le mot existe déja
//      int nb = ((Integer) k) + 1;
//      map.put(mot, nb); // mettre à jour le nombre de fois
//    }
//  }
//}
