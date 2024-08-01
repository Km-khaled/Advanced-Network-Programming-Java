package exo1_version_2;

import java.io.*;
import java.util.*;

public class A {

    public static HashMap<String, List<String>> loadSymptomMap() throws IOException {
        HashMap<String, List<String>> map = new HashMap<>();
        String file = A.class.getResource("symptomesnom.txt").getFile();
        try (Scanner scanner = new Scanner(new File(file)).useDelimiter(" ")) {
            while (scanner.hasNext()) {
                String nom = scanner.next();
                String symptome = scanner.nextLine().trim();
                map.computeIfAbsent(nom, k -> new ArrayList<>()).add(symptome);
            }
        }
        return map;
    }
}
