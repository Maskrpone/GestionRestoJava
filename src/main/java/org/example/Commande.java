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

    //region Getter

    public int getTable() {
        return table;
    }

    public boolean isEnPreparation() {
        return enPreparation;
    }

    public ArrayList<HashMap<Produits, Integer>> getProduits() {
        return produits;
    }

    //endregion

    //region Setter

    public void setTable(int table) {
        this.table = table;
    }

    public void setEnPreparation(boolean enPreparation) {
        this.enPreparation = enPreparation;
    }

    public void setProduits(ArrayList<HashMap<Produits, Integer>> produits) {
        this.produits = produits;
    }

    //endregion
}
