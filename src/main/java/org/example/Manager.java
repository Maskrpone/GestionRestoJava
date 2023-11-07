package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Employe {
    private HashMap<String, ArrayList<Employe>> employes;
    private ArrayList<Commande> historique_commandes;
    public Manager(String nom, String prenom, int salaire,String poste , HashMap<String, Integer> stock) {
        super(nom, prenom, salaire);
        this.employes = new HashMap<String, ArrayList<Employe>>();
    }

    public HashMap<String, ArrayList<Employe>> getEmployes() {
        return employes;
    }

    public ArrayList<Commande> getHistorique_commandes() {
        return historique_commandes;
    }
    public void finJournee() {}
}
