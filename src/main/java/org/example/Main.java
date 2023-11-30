package org.example;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Serveur serveur = new Serveur("Doe", "John", 1200, "serveur");

        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("limonade", 1);
        Produits limonade = new Produits("Limonade",5,ingredients);
        ArrayList<Produits> boissons = new ArrayList<>();

        HashMap<String, Integer> ingredients_repas = new HashMap<>();
        ingredients_repas.put("pain", 1);
        ingredients_repas.put("tomate", 1);
        ingredients_repas.put("salade", 1);
        ingredients_repas.put("steak", 1);

        Produits burger = new Produits("burger", 15,ingredients_repas);

        boissons.add(0, limonade);
        ArrayList<Produits> repas = new ArrayList<>();
        repas.add(0, burger);

        serveur.addCommande(15,boissons,repas);
    }
}

