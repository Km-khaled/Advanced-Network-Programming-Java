package EXO5;



import java.io.*;

public class AA {

public static void main(String[] args) throws IOException {

File inputFile = new File("./src/EXO5/test1.txt");

File outputFile = new File("./src/EXO5/test2.txt");

FileReader in = new FileReader(inputFile);

FileWriter out = new FileWriter(outputFile,true);

int c;

while ((c = in.read()) != -1) out.write(c);

in.close(); out.close();

}

}