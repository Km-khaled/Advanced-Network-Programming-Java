package EXO6;

import java.io.*;








public class AAA {

public static void main(String [ ] args) {




String m = "mirsdglklul";
File fichier= new File("./src/EXO6/test3.txt");
try{
    FileWriter flotEcriture = new FileWriter(fichier);
int length=m.length();
for(int i=1 ;i<=length/2+1;i++){
        flotEcriture.write(m,i-1,length-2*(i-1));
    flotEcriture.write("\n");
    if(i<length/2+1){
    for(int j=0;j<i;j++){
        flotEcriture.write(" ");
    }
    }

}

for(int j=length/2,i=1;j>=1;j--,i+=2){
    for(int k=0;k<j-1;k++){
            flotEcriture.write(" ");

    }
            flotEcriture.write(m,j-1,i+2);
                flotEcriture.write("\n");


}

flotEcriture.close();
}catch (IOException e) { }

}
    
}