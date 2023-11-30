package org.example;
import java.util.ArrayList;

public class Commande {
    private final int table;
    private final ArrayList<Produits> repas;
    private final ArrayList<Produits> boissons;
    private boolean enPreparation;


    //region Constructor
    public Commande(int table, ArrayList<Produits> boissons, ArrayList<Produits> repas) {
        this.table = table;
        this.boissons = boissons;
        this.repas = repas;
        this.enPreparation = false;
    }
    //endregion

    //region getters
    public int getTable() {
        return table;
    }

    public ArrayList<Produits> getRepas() {
        return repas;
    }

    public ArrayList<Produits> getBoissons() {
        return boissons;
    }
    //endregion

    public void setEnPreparation(boolean b) {
        this.enPreparation = b;
    }
}