package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

import java.util.ArrayList;
public class Stock {
    private ArrayList<Ingredients> stock;
    private ArrayList<Boisson> stockBoisson;

    //region Constructor
    /**
     * Constructeur de la classe stock, s'initialisant avec une désérialisation.
     * Chaque ingrédient a son fichier .ser
     * @author Hippolyte
     * @serialData les fichiers de chaque ingrédients
     */
    public Stock() {
        this.stock = new ArrayList<>();
        this.stockBoisson = new ArrayList<>();
        // tableau contenant tous les ingrédients
        String dossier = "stock/Ingredients/";
        String[] liste_ingredient = new String[] {"salade.ser", "tomate.ser", "oignon.ser", "champignon.ser", "pain.ser", "steak.ser", "patate.ser", "fromage.ser", "saucisse.ser", "pate.ser"};
        // pour chaque ingrédient du tableau on va récupérer l'objet dans son fichier .ser et on l'insert dans le stock
        for (String s : liste_ingredient) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dossier + s))) {
                Ingredients ing = (Ingredients) ois.readObject();
                this.stock.add(ing);
                // System.out.println("Ingrédients récupérés: " + ing.getNom());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Impossible de lire : " + e);
            }
        }
        String dossierBoisson = "stock/Boissons";
        String[] liste_boisson = new String[]{"limonade.ser", "cidre.ser", "biere.ser", "jus.ser", "eau.ser"};
        for (String s1 : liste_boisson) {
            String cheminFichierBoisson = dossierBoisson + File.separator + s1;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichierBoisson))) {
                Boisson ing = (Boisson) ois.readObject();
                this.stockBoisson.add(ing);
                // System.out.println("Boisson récupérée: " + ing.getName());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Impossible de lire : " + e);
            }
        }
    }

    /**
     * Constructeur de la classe stock, s'initialisant avec une liste d'ingrédients passée en paramètre
     * @author  Hippolyte
     * @param _stock la liste d'ingrédients
     */
    public Stock(ArrayList<Ingredients> _stock) {
        this.stock = _stock;
    }

    //endregion

    //region Getter

    /**
     * Obtenir la quantité d'un ingrédient dans le stock
     * @author Hippolyte
     * @param ingredient le nom de l'ingrédient
     * @return Integer, la quantité de cet ingrédient encore dans le stock
     */
    public int getIngredient(String ingredient) {
        for (Ingredients it : this.stock) {
            if (it.getNom().equals(ingredient)) {
                return it.getNb();
            }
        }
        return 0; // on renvoie 0 si l'on n'a pas trouvé l'objet dans notre stock
    }

    /**
     * Obtenir l'objet Ingredients de l'ingrédient que l'on désire
     * @param ingredient le nom de l'ingrédient que l'on souhaite obtenir
     * @return objet Ingredients
     */
    public Ingredients getObjIngredient(String ingredient) {
        for (Ingredients it : this.stock) {
            if (it.getNom().equals(ingredient)) {
               return it;
            }
        }
        return new Ingredients();
    }

    public ArrayList<Ingredients> getStock() {
        return stock;
    }
    public ArrayList<Boisson> getStockBoisson() {
        return stockBoisson;
    }
    //endregion

    //region Setter
    /**
     * Cette fonction est utilisée pour retirer la quantité d'ingrédient que nous utilisons des stocks
     * @author Hippolyte
     * @param ingredient le nom de l'ingrédient
     * @param quantity la quantité utilisée et à retirer
     */
    public void useStock(String ingredient, int quantity) {
        for (Ingredients it : this.stock) {
            if (it.getNom().equals(ingredient)) {
                it.setNb(it.getNb() - quantity);
            }
        }
    }

    public void useStockBoisson(Boisson boisson) {
        for (Boisson it : this.stockBoisson){
            if(it.getName().equals(boisson.getName())){
                System.out.println(it.getNb());
                it.setNb(it.getNb() - 1);
                System.out.println(it.getNb());
                it.ecritureFichier(false);
            }
        }
    }

    public void setStock(ArrayList<Ingredients> _stock) {
        this.stock = _stock;
    }

    public void setIngredient(String nom, int quantity) {
        for (Ingredients it : this.stock) {
            if (it.getNom().equals(nom)) {
                it.setNb(quantity);
            }
        }
    }
    //endregion

    public void displayStock() {
        System.out.println("--------------------------------------------------");
        System.out.println("|\t\t   Nom de l'ingrédient   \t\t|\t\t        Quantité        \t\t|");
        System.out.println("--------------------------------------------------");
        for (Ingredients ingredient : stock) {
            System.out.printf("|\t %-15s \t\t\t|\t %-18s \t\t\t|\n", ingredient.getNom(), ingredient.getNb());
        }
        System.out.println("--------------------------------------------------");
    }
}
