package org.example;

import java.util.HashMap;

public class Produits {
    private final String nom;
    private final int prix;
    private final HashMap<String, Integer> ingredients;

    //region Constructor
    public Produits(String nom, int prix, HashMap<String, Integer> ingredients) {
        this.nom = nom;
        this.prix = prix;
        this.ingredients = ingredients;
    }
    //endregion

    //region Getters
    public String getNom() {
        return nom;
    }

    public int getPrice() {
        return prix;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }
    //endregion
}
