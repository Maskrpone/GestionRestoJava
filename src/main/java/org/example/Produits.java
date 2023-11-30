package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Produits {
    private final String nom;
    private final int price;
    private boolean estfini;
    private int tempsPrep;
    private final HashMap<String, Integer> ingredients;

    public Produits(String nom, int price, HashMap<String, Integer> ingredient, int tempsPrep) {
        this.nom = nom;
        this.price = price;
        this.ingredients = ingredient;
        this.tempsPrep = tempsPrep;
        this.estfini = false;
    }

    //region Getter
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

    //region Setter

    public void setEstfini(boolean estfini) {
        this.estfini = estfini;
    }

    //endregion

    @Override
    public String toString() {
        return "Plat{" +
                "name='" + nom + '\'' +
                ", ingredients=" + ingredients +
                ", price=" + price +
                '}';
    }

}
