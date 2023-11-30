package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Cuisinier extends Preparateur{
    private Menu menu;

    //region Constructor
    public Cuisinier(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
        try {
            this.menu = new Menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //endregion

    //region Getter

    public Menu getMenu() {
        return menu;
    }

    //endregion

    //region Methode
    public void cuisinerCommande(Commande commande) {
        System.out.println("Cuisinier en train de préparer la commande pour la table " + commande.getTable());
        commande.setEnPreparation(true);
        // Parcourir la liste des plats de la commande
        for (HashMap<Produits, Integer> platCommande : commande.getProduits()) {
            for (Produits produit : platCommande.keySet()) {
                int quantite = platCommande.get(produit);

                // Trouver le temps de préparation du plat dans le menu
                int tempsPrep = trouverTempsPreparation(produit);
                for (Map.Entry<String, Integer> entry : produit.getIngredients().entrySet()){

                }
                // Simuler la cuisson en attente du temps de préparation
                attendre(tempsPrep);


                System.out.println("Plat " + produit.getNom() + " prêt pour la table " + commande.getTable() + " (Quantité: " + quantite + ")");
            }
        }

        System.out.println("Commande pour la table " + commande.getTable() + " est prête !");
    }

    private int trouverTempsPreparation(Produits produit){
        for(Plat p : menu.getCartePlat()){
            if(p.getName().equals(produit.getNom())){
                return p.getTempsPrep();
            }
        }
        return -1;
    }

    private void attendre(int tempsAttente) {
        try {
            // Simuler l'attente en millisecondes (vous pouvez ajuster cette logique en fonction de vos besoins)
            Thread.sleep(tempsAttente * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void supprimerDesStocks(HashMap<String, Integer> ingredients, Stock stock){
        for(Map.Entry<String, Integer> entry : ingredients.entrySet()){

        }
    }

    //endregion
}
