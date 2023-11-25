package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Menu {
    private ArrayList<Plat> cartePlat;
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
            Plat plat = new Plat(
                    platNom,
                    j.jsonArrayToList(platData.getJSONArray("ingredients")),
                    platData.getInt("prix"),
                    platData.getInt("preparation")
            );

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

    public ArrayList<Plat> getCartePlat() {
        return cartePlat;
    }

    public ArrayList<Boisson> getCarteBoisson() {
        return carteBoisson;
    }
    //endregion
}