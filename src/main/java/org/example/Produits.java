package org.example;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Produits {
    private final String nom;
    private final int price;
    private boolean estfini;
    private final HashMap<String, Integer> ingredients;
    private int tempsPrep;

    public Produits(String nom, int price, HashMap<String, Integer> ingredients, int tempsPrep) {
        this.nom = nom;
        this.price = price;
        this.ingredients = ingredients;
        this.tempsPrep = tempsPrep;
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

    public boolean isEstfini() {
        return estfini;
    }

    public int getTempsPrep() {
        return tempsPrep;
    }
    //endregion

    //region Methode
    public void updateIngredientsList(JSONArray jsonIngredients) {
        for (int i = 0; i < jsonIngredients.length(); i++) {
            String ingredientName = jsonIngredients.getString(i);

            // Vérifier si l'ingrédient est déjà présent dans la liste
            if (ingredients.containsKey(ingredientName)) {
                // Si présent, incrémenter la quantité
                int currentQuantity = ingredients.get(ingredientName);
                ingredients.put(ingredientName, currentQuantity + 1);
            } else {
                // Sinon, ajouter l'ingrédient à la liste avec une quantité de 1
                ingredients.put(ingredientName, 1);
            }
        }
    }
    //endregion

    @Override
    public String toString() {
        return "Plat{" +
                "name='" + nom + '\'' +
                ", ingredients=" + ingredients +
                ", price=" + price +
                ", tempsPrep=" + tempsPrep +
                '}';
    }
}
