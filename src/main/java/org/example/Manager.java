package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @class  Manager est une classe fille de Employe
 * @see Employe est une classe mère des différents employés du restaurant, occupant des postes différents
 * @author Hippolyte & Thibaut
 *
 * @method embaucher() permet d'embaucher un employé
 * @method licencier() permet de licencier un employé
 * @method displayEmployesDisponibles() permet d'afficher les employés disponibles
 * @method ingredientsACommanderRapide() permet de commander rapidement des ingrédients
 * @method consulterStock() permet de consulter le stock
 * @method consulterStockPriceToPay() permet de consulter le stock et d'avoir le prix de l'augmentation du stock
 * @method finJournee() permet de finir la journée
 * @method selectTeam() permet de sélectionner une équipe
 * @method setStock() permet de modifier le stock
 *
 */
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

    /**
     * Permet de sélectionner une équipe
     * @return le hashmap d'une équipe qu'on chargera dans l'attribut employé du resto
     */
    public HashMap<String, Employe> selectTeam() {
        return null;
    }
    public void setStock(Stock stock) {

    }

    public void setHistorique_commandes(ArrayList<Commande> historique_commandes) {
        this.historique_commandes = historique_commandes;
    }

    //endregion

    //region MethodesStock
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

    //region MethodesEmployes

    /**
     * Permet de créer un employé et de l'ajouter à la liste des employés disponibles
     * @author  Hippolyte
     */
    public void embaucher() {
        System.out.println("Créez le profil de l'employé :");
        Scanner scanner = new Scanner(System.in);

        // on récupère le nom, prénom, salaire et poste de l'employé
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Salaire : ");
        int salaire = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Poste : ");
        String poste = scanner.nextLine();
        Employe employe = new Employe(nom, prenom, salaire, poste);

        // on écrit le profil employé dans le répertoire "employes/"
        employe.ecrireEmploye();
        System.out.println("Profil employé créé, il a été ajouté aux employés disponibles.");
    }

    /**
     * Permet de licencier un employé et de supprimer son profil du répertoire "employes/" s'il existe
     * @author Hippolyte
     */
    public void licencier() {
        System.out.println("Vous êtes sur l'écran de licenciement : ");
        Scanner scanner = new Scanner(System.in);

        // on récupère le nom et prénom de l'employé à licencier
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        // On récupère le fichier de l'employé
        final String fichier_employe = "employes/" + nom + "_" + prenom + ".ser";
        File employe = new File(fichier_employe);

        // Si le fichier existe, on le supprime
        if (employe.exists()) {
            if (employe.delete()) {
                System.out.println("L'employé a été licencié.");
            } else {
                System.err.println("Erreur lors de la suppression du fichier : " + fichier_employe);
            }
        } else {
            System.out.println("Cet employé n'existe pas.");
        }

    }

    /**
     * Permet d'afficher les différents employés disponibles
     * @author  Hippolyte
     */
    public void displayEmployesDisponibles() {
        // on récupère les fichiers contenant les différents employés
        // (un fichier par employé)
        final String dossier = "employes/";
        File dossierEmploye = new File(dossier);

        // on vérifie que le dossier existe et qu'il s'agit bien d'un dossier
        if (dossierEmploye.exists() && dossierEmploye.isDirectory()) {
            ArrayList<Employe> employes = new ArrayList<>();

            // on récupère les fichiers contenus dans le dossier
            File[] fichiersEmployes = dossierEmploye.listFiles();

            // on désérialise les fichiers employés et on les ajoute à la liste
            if (fichiersEmployes != null) {
                for (File fichierEmploye : fichiersEmployes) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichierEmploye))) {
                        Employe employe = (Employe) ois.readObject();
                        employes.add(employe);
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Impossible de lire : " + e);
                    }
                }
            }
            // affichage de chaque employé disponible
            if (!employes.isEmpty()) {
                System.out.println("Employés disponibles :");
                for (Employe employe : employes) {
                    employe.afficher();
                }
            } else {
                System.out.println("Aucun employé disponible.");
            }
        } else {
            System.err.println("Impossible d'accéder au dossier : " + dossier);
        }
    }

    public ArrayList<Employe> formerEquipe() {
        return null;
    }
}
