package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.write;

public class JSON {
    public void writeFile(String filename, String content) {
        try {
            Path path = Paths.get(filename);
            write(path, content.getBytes());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public String getFile(String filename) {
        try {
            Path path = Paths.get(filename);
            return new String(readAllBytes(path));
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return "";
    }

    public void writeStock(String varname, int addition) {
        String filename = "stock.json";
        try {
            // on récupère le chemin jusqu'au fichier
            Path path = Paths.get(filename);
            // on convertie l'objet JSON en String
            String objString = new String(readAllBytes(path));
            // on convertie le String en objet JSON
            JSONObject o = new JSONObject(objString);
            // on récupère la valeur de la variable
            int stock = o.getInt(varname);
            // on ajoute la valeur addition à la variable stock
            o.put(varname, stock + addition);
            // on écrit le fichier
            write(path, o.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
    public void writeFullStock(Map<Ingredients, Integer> ingredientsMap, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            JSONArray jsonArray = new JSONArray();

            for (Map.Entry<Ingredients, Integer> entry : ingredientsMap.entrySet()) {
                Ingredients ingredient = entry.getKey();
                int quantity = entry.getValue();

                JSONObject ingredientJson = new JSONObject();
                ingredientJson.put("nom", ingredient.getNom());
                ingredientJson.put("quantite", quantity);
                ingredientJson.put("prix", ingredient.getPrice());

                JSONObject entryJson = new JSONObject();
                entryJson.put(ingredient.getNom(), ingredientJson);

                jsonArray.put(entryJson);
            }

            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
