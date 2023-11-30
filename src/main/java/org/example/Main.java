package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        System.out.println(menu.getCartePlat().get(2));

        /*
        Ingredients i = new Ingredients("champignon", 1, 20);
        Ingredients i1 = new Ingredients("fromage", 2, 20);
        Ingredients i2 = new Ingredients("oignon", 1, 20);
        Ingredients i3 = new Ingredients("pain", 1, 20);
        Ingredients i4 = new Ingredients("patate", 1, 20);
        Ingredients i5 = new Ingredients("salade", 1, 20);
        Ingredients i6 = new Ingredients("saucisse", 2, 20);
        Ingredients i7 = new Ingredients("steak", 3, 20);
        Ingredients i8 = new Ingredients("tomate", 1, 20);
        i.ecritureFichier();
        i1.ecritureFichier();
        i2.ecritureFichier();
        i3.ecritureFichier();
        i4.ecritureFichier();
        i5.ecritureFichier();
        i6.ecritureFichier();
        i7.ecritureFichier();
        i8.ecritureFichier();

         */
        /*
        ArrayList<HashMap<Produits, Integer>> produits;
        ArrayList<Produits> plat = new ArrayList<>();
        plat.add(menu.getCartePlat().get(2));
        Commande commande = new Commande(12, plat, plat);
        Cuisinier cuisinier = new Cuisinier("Bob", "Leponge", 2, "Cuisinier");
        cuisinier.cuisinerCommande(commande);


         */
        Manager m = new Manager("VDP", "Mathieu", 2, "manager");
        m.consulterStock();

    }
}

