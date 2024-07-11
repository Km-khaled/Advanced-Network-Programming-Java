
package EXO2;

import java.io.Serializable;

public class Personne implements Serializable {

public String nom;
transient String prenom;
transient int age;

    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" + "nom=" + nom + ", prenom=" + prenom + ", age=" + age + '}';
    }




}