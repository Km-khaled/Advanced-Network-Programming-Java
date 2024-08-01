package exo1_version_1;

import java.io.*;
import java.util.*;

public class A {

    public static HashMap<String, Integer> loadSymptomMap() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        String file = A.class.getResource("symptomes.txt").getFile();
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                String symptom = scanner.nextLine();
                ajouter(map, symptom);
            }
        }
        return map;
    }

    static void ajouter(Map<String, Integer> map, String symptome) {
        map.compute(symptome, (key, value) -> (value == null) ? 1 : value + 1);
    }
}
