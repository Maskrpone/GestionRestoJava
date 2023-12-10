package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Boisson implements Serializable {
    private String name;
    private int price;
    private int nb;

    //region Constructor

    public Boisson(String name, int price, int nb) {
        this.name = name;
        this.price = price;
        this.nb = nb;
    }

    public Boisson(String name, int price) {
        this.name = name;
        this.price = price;
        this.nb = 0;
    }

    //endregion

    //region Getter

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNb() {
        return nb;
    }
//endregion

    //region Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    //endregion

    //region Methode

    /**
     * Fonction qui incrémente les stocks
     * @param nbToAdd nombre à incrémenter
     */
    public void addNb(int nbToAdd){
        this.setNb(this.getNb() + nbToAdd);
    }

    @Override
    public String toString() {
        return "Boisson{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + nb +
                '}';
    }

    /**
     * Permet d'écrire la boisson dans un fichier sérialisé
     * @param printConsole Message dans la console oui/non
     */
    public void ecritureFichier(Boolean printConsole){
        final String nom_dossier = "stock/Boissons/";
        String nomFichier = this.getName() + ".ser";
        String cheminFichier = nom_dossier + nomFichier;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(this);
            if(printConsole)
                System.out.println("L'ingrédient " + this.getName() + " est maintenant ajouté");


        } catch (IOException e) {
            System.out.println("Impossible d'écrire : " + e);
        }
    }


    //endregion
}
