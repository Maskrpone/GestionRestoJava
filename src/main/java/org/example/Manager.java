package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager extends Employe {
    private HashMap<String, ArrayList<Employe>> employes;
    private ArrayList<Commande> historique_commandes;

    //region Constructor
    public Manager(String nom, String prenom, int salaire,String poste) {
        super(nom, prenom, salaire, poste);
        this.employes = new HashMap<>();
    }
    //endregion

    //region Getter
    public HashMap<String, ArrayList<Employe>> getEmployes() {
        return employes;
    }

    public ArrayList<Commande> getHistorique_commandes() {
        return historique_commandes;
    }
    //endregion

    //region Setter

    public void setEmployes(HashMap<String, ArrayList<Employe>> employes) {
        this.employes = employes;
    }

    public void setStock(Stock stock) {

    }

    public void setHistorique_commandes(ArrayList<Commande> historique_commandes) {
        this.historique_commandes = historique_commandes;
    }

    //endregion

    //region Methode
    public void finJournee() {}

    /**
     * @author Thibaut
     * @param stockLow Valeur minimale dans stock
     * @param stockFull Valeur de stock full
     * @param stock Le stock à controler
     * @return Le prix total à payer
     */
    public int ingredientsACommanderRapide(int stockLow, int stockFull, Stock stock) {
        Map<Ingredients, Integer> ingredientsACommander = new HashMap<>();
        int priceToPay = 0;

        for (Ingredients ingredient : stock.getStock()) {
            if (ingredient.getNb() <= stockLow) {
                int quantiteAAcheter = stockFull - ingredient.getNb();
                ingredient.setNb(stockFull);
                priceToPay += quantiteAAcheter * ingredient.getPrice();
                ingredientsACommander.put(ingredient, quantiteAAcheter);
            }
        }
        System.out.println(ingredientsACommander);
        return priceToPay;
    }

    /**
     * @author Thibaut
     * @param stock Le stock à consulter
     * @return La map d'ingrédient commandé avec la quantité
     */
    public Map<Ingredients, Integer> consulterStock(Stock stock) {
        ArrayList<Ingredients> currentStock = stock.getStock();
        Map<Ingredients, Integer> quantitiesAdded = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int index = 1;
            System.out.println("Stock actuel :");

            for (Ingredients ingredient : currentStock) {
                int quantite = ingredient.getNb();
                System.out.println(index + ". " + ingredient.getNom() + ": " + quantite);
                index++;
            }

            System.out.print("Sélectionnez le numéro de l'ingrédient à augmenter (0 pour quitter) : ");
            int selectedNumber = scanner.nextInt();


            if (selectedNumber == 0) {
                break; // Quitte la boucle si 0 est sélectionné
            } else if (selectedNumber > 0 && selectedNumber <= currentStock.size()) {
                Ingredients selectedIngredient = null;
                int currentIndex = 1;

                for (Ingredients ingredient : currentStock) {
                    if (currentIndex == selectedNumber) {
                        selectedIngredient = ingredient;
                        break;
                    }
                    currentIndex++;
                }

                assert selectedIngredient != null;
                System.out.print("Entrez la quantité à ajouter pour " + selectedIngredient.getNom() + " : ");
                int quantityToAdd = scanner.nextInt();

                if (quantityToAdd > 0) {
                    selectedIngredient.addNb(quantityToAdd);
                    quantitiesAdded.put(selectedIngredient, quantityToAdd);
                    System.out.println("Stock mis à jour.");
                } else {
                    System.out.println("La quantité doit être supérieure à 0. Aucune modification effectuée.");
                }
            } else {
                System.out.println("Numéro invalide. Aucune modification effectuée.");
            }
        }
        return quantitiesAdded;
    }


    /**
     * Utilise la fonction consulterStock et permet d'avoir le prix au lieu d'une map
     * @author Thibaut
     * @param stock Le stock à consulter
     * @return Le prix de l'augmentation stock
     */
    public int consulterStockPriceToPay(Stock stock){
        Map<Ingredients, Integer> ingredientAchetes;
        ingredientAchetes = consulterStock(stock);
        int priceToPay = 0;

        for(Map.Entry<Ingredients, Integer> entry : ingredientAchetes.entrySet()){
            Ingredients ingredient = entry.getKey();
            priceToPay += ingredient.getPrice()*entry.getValue();
        }

        return priceToPay;
    }

    //endregion
}
