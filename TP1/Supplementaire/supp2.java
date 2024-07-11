/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Supplementaire;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class supp2 {

    static class Symptomenbre {
        String symptome;
        int count;

        Symptomenbre(String symptome, int count) {
            this.symptome = symptome;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Symptomenbre> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("./src/Supplementaire/symptomes.txt"))) {
            while (scanner.hasNextLine()) {
                ajouter(list, scanner.nextLine());
            }
        }

        list.sort((a, b) -> Integer.compare(b.count, a.count));

        File outputFile = new File("./src/Supplementaire/res.txt");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Symptomenbre symptomeCount : list) {
                writer.write(symptomeCount.symptome + ": " + symptomeCount.count);
                writer.newLine();
            }
        }
    }

    static void ajouter(List<Symptomenbre> list, String symptome) {
        for (Symptomenbre symptomeCount : list) {
            if (symptomeCount.symptome.equals(symptome)) {
                symptomeCount.count++;
                return; 
            }
        }
        list.add(new Symptomenbre(symptome, 1));
    }
}
