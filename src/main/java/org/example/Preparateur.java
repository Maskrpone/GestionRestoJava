package org.example;

public class Preparateur extends Employe{
    private String poste;
    public Preparateur(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire);
        this.poste = poste;
    }
    public void prepare(Commande commande) {

    }
}
