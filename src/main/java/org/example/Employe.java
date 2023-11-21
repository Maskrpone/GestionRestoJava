package org.example;

import java.lang.reflect.Array;
import java.util.Map;

public class Employe {
    private final String nom;
    private final String prenom;
    private final int salaire;
    private int jours_travailles;
    private String poste;

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
        this.jours_travailles = 0;
        this.poste = poste;
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

}
