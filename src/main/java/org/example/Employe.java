package org.example;

public class Employe {
    private final String nom;
    private final String prenom;
    private final int salaire;
    private final String poste;

    private int jours_travailles;

    public void setJours_travailles(int jours_travailles) {
        this.jours_travailles = jours_travailles;
    }

    public int getJours_travailles() {
        return jours_travailles;
    }

    public Employe(String nom, String prenom, int salaire, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.poste = poste;
        this.jours_travailles = 0;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getSalaire() {
        return salaire;
    }
    public String getPoste() {
        return poste;
    }

}
