package org.example;

import java.util.HashMap;

public class Produits {
    private final String nom;
    private final int price;
    private boolean estfini;
    private final HashMap<String, Integer> ingredients;

    public Produits(String nom, int price, int tempsPrep, HashMap<String, Integer> ingredients) {
        this.nom = nom;
        this.price = price;
        this.ingredients = ingredients;
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
