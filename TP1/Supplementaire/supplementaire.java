
package Supplementaire;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class supplementaire {

    public static void trie(File file1, File file2, File outputFile) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(numbers);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int num : numbers) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File imp = new File("./src/Supplementaire/imp.txt");
        File pair = new File("./src/Supplementaire/pair.txt");
        File rslt = new File("./src/Supplementaire/result.txt");

        trie(imp, pair, rslt);
    }
}
