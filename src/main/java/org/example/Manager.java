package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager extends Employe {
    private HashMap<String, ArrayList<Employe>> employes;
    private Stock stock;
    private ArrayList<Commande> historique_commandes;

    //region Constructor
    public Manager(String nom, String prenom, int salaire,String poste) {
        super(nom, prenom, salaire, poste);
        this.employes = new HashMap<String, ArrayList<Employe>>();
    }
    public Manager(String nom, String prenom, int salaire,String poste , Stock _stock) {
        super(nom, prenom, salaire, poste);
        this.employes = new HashMap<String, ArrayList<Employe>>();
        this.stock = _stock;
    }
    //endregion

    //region Getter
    public HashMap<String, ArrayList<Employe>> getEmployes() {
        return employes;
    }

    public ArrayList<Commande> getHistorique_commandes() {
        return historique_commandes;
    }

    public Stock getStock() {
        return stock;
    }
    //endregion

    //region Setter

    public void setEmployes(HashMap<String, ArrayList<Employe>> employes) {
        this.employes = employes;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setHistorique_commandes(ArrayList<Commande> historique_commandes) {
        this.historique_commandes = historique_commandes;
    }

    //endregion

    //region Methode
    public void finJournee() {}
    public Map<Ingredients, Integer> ingredientsACommanderRapide() {
        Map<Ingredients, Integer> ingredientsACommander = new HashMap<>();
        int priceToPay = 0;

        for (Ingredients ingredients : stock.getStock()) {
            Ingredients ingredient = entry.getKey();
            if (entry.getValue() <= 10) {
                int quantiteAAcheter = 50 - entry.getValue();
                priceToPay += quantiteAAcheter * ingredient.getPrice();
                ingredientsACommander.put(ingredient, quantiteAAcheter);
            }
        }
        System.out.println(priceToPay);
        return ingredientsACommander;
    }

    public Map<Ingredients, Integer> consulterStock() {
        Map<Ingredients, Integer> currentStock = stock.getStock();
        Map<Ingredients, Integer> quantitiesAdded = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        JSON j = new JSON();

        while (true) {
            int index = 1;
            System.out.println("Stock actuel :");

            for (Map.Entry<Ingredients, Integer> entry : currentStock.entrySet()) {
                Ingredients ingredient = entry.getKey();
                int quantite = entry.getValue();
                System.out.println(index + ". " + ingredient.getClass().getSimpleName() + ": " + quantite);
                index++;
            }

            System.out.print("Sélectionnez le numéro de l'ingrédient à augmenter (0 pour quitter) : ");
            int selectedNumber = scanner.nextInt();


            if (selectedNumber == 0) {
                break; // Quitte la boucle si 0 est sélectionné
            } else if (selectedNumber > 0 && selectedNumber <= currentStock.size()) {
                Ingredients selectedIngredient = null;
                int currentIndex = 1;

                for (Ingredients ingredient : currentStock.keySet()) {
                    if (currentIndex == selectedNumber) {
                        selectedIngredient = ingredient;
                        break;
                    }
                    currentIndex++;
                }

                System.out.print("Entrez la quantité à ajouter pour " + selectedIngredient.getClass().getSimpleName() + " : ");
                int quantityToAdd = scanner.nextInt();

                if (quantityToAdd > 0) {
                    currentStock.put(selectedIngredient, currentStock.get(selectedIngredient) + quantityToAdd);
                    quantitiesAdded.put(selectedIngredient, quantityToAdd);
                    System.out.println("Stock mis à jour.");
                } else {
                    System.out.println("La quantité doit être supérieure à 0. Aucune modification effectuée.");
                }
            } else {
                System.out.println("Numéro invalide. Aucune modification effectuée.");
            }
        }
        j.writeFullStock(currentStock, "Stock.json");
        return quantitiesAdded;
    }

    //endregion
}
