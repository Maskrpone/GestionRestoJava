package org.example;

import java.io.Serializable;

public class Ingredients implements Serializable {
    private String nom;
    private int price;
    private int nb;
    public Ingredients() {
        this.nom = "Null";
        this.price = -1;
        this.nb = -1;
    }

    public Ingredients(String nom, int price, int nb) {
        this.nom = nom;
        this.price = price;
        this.nb = nb;
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

    public int getNb() {return this.nb;}

    public void setNb(int nb) {
        this.nb = nb;
    }
    @Override
    public String toString() {
        return "Ingredients{" +
                "nom:'" + nom + '\'' +
                ", price:" + price +
                '}';
    }

    //region Methode
    public void addNb(int nbToAdd){
        this.setNb(this.getNb() + nbToAdd);
    }

    //endregion
}
