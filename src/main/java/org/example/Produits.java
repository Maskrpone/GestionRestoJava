package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Produits {
    private final String nom;
    private final int price;
    private boolean estfini;
    private int tempsPrep;
    private final HashMap<String, Integer> ingredients;

    public Produits(String nom, int price, int tempsPrep, HashMap<String, Integer> ingredients) {
        this.nom = nom;
        this.price = price;
        this.ingredients = ingredients;
        this.estfini = false;
    }
    //endregion

    //region Getters
    public String getNom() {
        return nom;
    }

    public int getPrice() {
        return price;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }
    //endregion
}
