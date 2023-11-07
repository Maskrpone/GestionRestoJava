package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Commande {
    private int table;
    private boolean enPreparation;

    // Tableau contenant plusieurs Hashmap. Le Hashmap contient un des produits commandés, et le nombre de ce produit commandé.
    private ArrayList<HashMap<Produits, Integer>> produits;
    public Commande(int table, ArrayList<HashMap<Produits, Integer>> produits) {
        this.table = table;
        this.produits = produits;
    }
}
