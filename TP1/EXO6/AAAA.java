package EXO6;

import java.io.*;


public class AAAA {

public static void main(String [ ] args) {




String m = "mirsdglfg";
String m2=null,m3=null,m4=null;
File fichier= new File("./src/EXO6/test3.txt");
try{
    FileWriter flotEcriture = new FileWriter(fichier);
    int length=m.length();

for(int i=1 ;i<=length/2+1;i++){
        flotEcriture.write(m,0,i);
        
        for(int j=i ;j<=length-i;j++){
   
            flotEcriture.write(" ");
        }
        
        if(i<=length/2){
                            flotEcriture.write(m,length-i,i);

        }
        else{
                    flotEcriture.write(m,length-i+1,i-1);

        }

                    flotEcriture.write("\n");

    }

for(int i= length/2;i>=1;i--){
            flotEcriture.write(m,0,i);
for(int j= i;j<length-i;j++){
                    flotEcriture.write(" ");

}
                    flotEcriture.write(m,length-i,i);
                    flotEcriture.write("\n");

}
    





flotEcriture.close();
}catch (IOException e) { }
}
    
}
