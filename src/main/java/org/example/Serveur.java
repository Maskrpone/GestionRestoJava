package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Serveur extends Employe{

    //region Constructor
    public Serveur(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
    }

    //endregion

    public void addCommande(int table, ArrayList<Produits> boissons, ArrayList<Produits> repas) {
        int produit_impossible = 0;
        for (Produits it : boissons) {
            if(inStock(it)) {
                System.out.println("Nous pouvons produire " + it.getNom());
            } else {
                System.err.println("Nous ne pouvons pas produire " + it.getNom());
                produit_impossible++;
            }
        }
        for (Produits it : repas) {
            if(inStock(it)) {
                System.out.println("Nous pouvons produire " + it.getNom());
            } else {
                System.err.println("Nous ne pouvons pas produire " + it.getNom());
                produit_impossible++;
            }
        }
        if (produit_impossible == 0) {
            Commande commande = new Commande(table, boissons, repas);
            //TODO: ajouter la 'commande.envoi()'
            System.out.println("Commande envoyée à la cuisine et au bar.");
        }
    }

    /**
     * Fonction qui renvoie True s'il y a suffisemment d'ingrédients dans le stock et False sinon.
     * @author Hippolyte
     * @param prod qui est le produit dont on veut controler la disponibilité des ingrédients
     * @return Boolean
     */
    public boolean inStock(Produits prod) {
        Stock stock = new Stock();
        for (String key : prod.getIngredients().keySet()) {
            if (stock.getIngredient(key) < prod.getIngredients().get(key)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifCommande(Commande commande) {
        return true;
    }

    public void servirCommande(Commande commande) {
    }


}
