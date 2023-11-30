package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Commande {
    private final int table;
    private final ArrayList<Produits> repas;
    private final ArrayList<Produits> boissons;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    //region Constructor
    public Commande(int table, ArrayList<Produits> boissons, ArrayList<Produits> repas) {
        this.table = table;
        this.boissons = boissons;
        this.repas = repas;
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
}