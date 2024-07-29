package exo3;

import java.io.Serializable;

public class Joueur implements Serializable {

    private int numero;
    private String nom;
    private String equipe;

    public Joueur(int numero, String nom, String equipe) {
        this.numero = numero;
        this.nom = nom;
        this.equipe = equipe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "numero = " + numero + " nom = " + nom + " equipe = " + equipe;
    }

}
