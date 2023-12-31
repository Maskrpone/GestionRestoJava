package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Menu {
    private ArrayList<Produits> cartePlat;
    private ArrayList<Boisson> carteBoisson;


    //region Constructor

    public Menu() throws IOException {
        JSON j = new JSON();
        String jsonMenu = j.getFile("repas.json");
        String jsonBoisson = j.getFile("boissons.json");

        // Utiliser la bibliothèque org.json pour convertir le JSON en objets Java
        JSONObject menuObject = new JSONObject(jsonMenu);

        // Initialiser la liste des plats
        this.cartePlat = new ArrayList<>();
        this.carteBoisson = new ArrayList<>();

        // Parcourir le menu et ajouter chaque plat à la liste
        Iterator<String> keys = menuObject.keys();
        while (keys.hasNext()) {
            String platNom = keys.next();
            JSONObject platData = menuObject.getJSONObject(platNom);

            // Créer une instance de Plat avec les données du plat actuel
            Produits plat = new Produits(
                    platNom,
                    platData.getInt("prix"),
                    new HashMap<>(),
                    platData.getInt("preparation")
            );

            // Mettre à jour la liste des ingrédients en fonction du JSON
            plat.updateIngredientsList(platData.getJSONArray("ingredients"));

            // Ajouter le plat à la liste
            cartePlat.add(plat);
        }

        JSONObject boissonsObject = new JSONObject(jsonBoisson);

        // Parcourir les boissons et copier les informations
        Iterator<String> key = boissonsObject.keys();
        while (key.hasNext()) {
            String boissonNom = key.next();
            int boissonPrix = boissonsObject.getInt(boissonNom);

            Boisson boisson = new Boisson(boissonNom, boissonPrix);
            carteBoisson.add(boisson);
        }
    }

    //endregion

    //region Getter

    public ArrayList<Produits> getCartePlat() {
        return cartePlat;
    }

    public ArrayList<Boisson> getCarteBoisson() {
        return carteBoisson;
    }
    //endregion

    //region Methode

    public void afficherMenu() {
        System.out.println("----- Carte des plats -----");
        afficherCartePlat();

        System.out.println("\n----- Carte des boissons -----");
        afficherCarteBoisson();
    }

    private void afficherCartePlat() {
        System.out.println("--------------------------------------------------");
        System.out.println("|\t\t   Nom du plat   \t\t|\t\t        Prix        \t\t|");
        System.out.println("--------------------------------------------------");
        for (Produits plat : cartePlat) {
            System.out.printf("|\t %-15s \t\t\t|\t %-18s \t\t\t|\n", plat.getNom(), plat.getPrice() + " euros");
        }
        System.out.println("--------------------------------------------------");
    }

    private void afficherCarteBoisson() {
        System.out.println("--------------------------------------------------");
        System.out.println("|\t   Nom de la boisson   \t|\t        Prix        \t|");
        System.out.println("--------------------------------------------------");
        for (Boisson boisson : carteBoisson) {
            System.out.printf("|\t %-21s \t|\t %-18s \t|\n", boisson.getName(), boisson.getPrice() + " euros");
        }
        System.out.println("--------------------------------------------------");
    }

    //endregion
}
