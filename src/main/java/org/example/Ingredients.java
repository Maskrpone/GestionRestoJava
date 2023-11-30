package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public void diminuerNb(int quantite) {
        if (nb >= quantite) {
            nb -= quantite;
        } else {
            // Gérer le cas où la quantité demandée est supérieure à la quantité disponible
            System.out.println("Erreur : Quantité insuffisante d'ingrédient " + nom);
        }
    }

    /**
     * Ecrit la classe dans fichier .ser
     * @param printConsole debug ou non
     */
    public void ecritureFichier(Boolean printConsole){
        final String nom_dossier = "stock/Ingredients/";
        String nomFichier = this.getNom() + ".ser";
        String cheminFichier = nom_dossier + nomFichier;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(this);
            if(printConsole)
                System.out.println("L'ingrédient " + this.getNom() + " est maintenant ajouté");


        } catch (IOException e) {
            System.out.println("Impossible d'écrire : " + e);
        }
    }

    //endregion
}
