
package EXO1;

import java.io.Serializable;
import java.io.Serializable;

public class Etudiant implements Serializable {
    private String nom;
    private String specialite;
    private int moy;

    public Etudiant(String nom, String specialite, int moy) {
        this.nom = nom;
        this.specialite = specialite;
        this.moy = moy;
    }

    public String getNom() {
        return nom;
    }

    public int getMoy() {
        return moy;
    }

       public String getSpecialite() {
        return specialite;
    }

    
    
    @Override
    public String toString() {
        return "Etudiant : " + nom + "   " + specialite + " : " + moy;
    }
}

