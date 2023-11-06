package org.example;

public class Ingredients {
    private String nom;
    private int price;

    public Ingredients(String nom, int price) {
        this.nom = nom;
        this.price = price;
    }

    public String getNom() {
        return nom;
    }

    public int getPrice() {
        return price;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
