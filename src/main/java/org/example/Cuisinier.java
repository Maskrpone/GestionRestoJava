package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Cuisinier extends Preparateur{
    private final Menu menu;

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

    /**
     * Cuisinier cuisine une commande
     * @param commande Commande à cuisiner
     */
    public void cuisinerCommande(Commande commande) {
        Thread thread = new Thread(() -> {
            System.out.println(this.getNom() + ", un de nos cuisinier, en train de préparer la commande pour la table " + commande.getTable());
            commande.setEnPreparation(true);
            // Parcourir la liste des plats de la commande
            for (Produits produit : commande.getRepas()) {
                // Trouver le temps de préparation du plat dans le menu
                int tempsPrep = trouverTempsPreparation(produit);

                // Supprimer des stocks
                supprimerDesStocks(produit.getIngredients());

                // Simuler la cuisson en attente du temps de préparation
                attendre(tempsPrep);

                System.out.println("Plat " + produit.getNom() + " prêt pour la table " + commande.getTable());
            }

            System.out.println("Commande des PLATS pour la table " + commande.getTable() + " est prête !");
        });

        thread.start();
    }

    /**
     * Trouve le temps d'attente du plat à partir de la classe menu
     * @param produit temps d'attente du produit cherché
     * @return temps d'attente
     */
    private int trouverTempsPreparation(Produits produit){
        for(Produits p : menu.getCartePlat()){
            if(p.getNom().equals(produit.getNom())){
                return p.getTempsPrep();
            }
        }
        return -1;
    }

    /**
     * Supprime des stocks les ingredients de la HashMap
     * @param ingredients ingredient qui sont utilises
     */
    private void supprimerDesStocks(HashMap<String, Integer> ingredients){
        Stock stock = new Stock();
        for(Map.Entry<String, Integer> entry : ingredients.entrySet()){
            stock.useStock(entry.getKey(), entry.getValue());
        }
    }

    //endregion
}
