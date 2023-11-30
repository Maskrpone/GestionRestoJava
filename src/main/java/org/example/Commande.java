package org.example;
import java.io.Serializable;
import java.util.ArrayList;

public class Commande implements Serializable {
    private final int table;
    private final ArrayList<Produits> repas;
    private final ArrayList<Boisson> boissons;
    private boolean enPreparation;


    //region Constructor
    public Commande(int table, ArrayList<Boisson> boissons, ArrayList<Produits> repas) {
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

    public ArrayList<Boisson> getBoissons() {
        return boissons;
    }
    //endregion

    public void setEnPreparation(boolean b) {
        this.enPreparation = b;
    }
}