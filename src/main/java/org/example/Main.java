package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Serveur serveur = new Serveur("Doe", "John", 1200, "serveur");

        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("pain", 1);
        ingredients.put("tomate", 1);
        ingredients.put("salade", 1);
        ingredients.put("steak", 1);
        Produits burger = new Produits("Hamburger", 10, ingredients, 1);

        ArrayList<String> ingredients2 = new ArrayList<>();
        ingredients2.add("limonade");
        Boisson limonade = new Boisson("Limonade", 5);

        ArrayList<Produits> repas = new ArrayList<>();
        repas.add(burger);
        ArrayList<Boisson> boissons = new ArrayList<>();
        boissons.add(limonade);

        serveur.addCommande(15,boissons, repas);

        //TODO: gérer l'exception lorsque les fichier .ser n'existent pas
        //TODO: faire en sorte d'avoir un stock par défaut (fonction qui init le stock pour le manager par ex)

        /*
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("limonade");
        Produits limonade = new Produits("Limonade",5, ingredients, 2);
        ArrayList<Produits> boissons = new ArrayList<>();

        HashMap<String, Integer> ingredients_repas = new HashMap<>();
        ingredients_repas.put("pain", 1);
        ingredients_repas.put("tomate", 1);
        ingredients_repas.put("salade", 1);
        ingredients_repas.put("steak", 1);

        Menu menu = new Menu();

        ArrayList<HashMap<Produits, Integer>> produits;
        produits.add(menu.getCartePlat());
        Cuisinier c = new Cuisinier("Philippe", "Philippe", 13, "cuisinier");
        c.cuisinerCommande();

        serveur.addCommande(15,boissons, repas);

         */

        Menu menu = new Menu();
        System.out.println(menu.getCartePlat());
        System.out.println(menu.getCarteBoisson());

    }
}

