package org.example;

import java.util.ArrayList;

public class Plat {
    private String name;
    private ArrayList<String> ingredients;
    private int price;
    private int tempsPrep;
    private boolean estfini;

    //region Constructor

    public Plat(String name, ArrayList<String> ingredients, int price, int tempsPrep) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.tempsPrep = tempsPrep;
        this.estfini = false;
    }

    //endregion

    //region Getter

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public int getPrice() {
        return price;
    }

    public int getTempsPrep() {
        return tempsPrep;
    }

    public boolean isEstfini() {
        return estfini;
    }

    //endregion

    //region Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTempsPrep(int tempsPrep) {
        this.tempsPrep = tempsPrep;
    }

    public void setEstfini(boolean estfini) {
        this.estfini = estfini;
    }

    //endregion

    //region Methode

    @Override
    public String toString() {
        return "Plat{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", price=" + price +
                ", tempsPrep=" + tempsPrep +
                '}';
    }

    //endregion
}
