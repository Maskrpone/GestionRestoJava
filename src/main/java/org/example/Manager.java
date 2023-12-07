package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Hippolyte & Thibaut
 * @class Manager est une classe fille de Employe
 * @method embaucher() permet d'embaucher un employé
 * @method licencier() permet de licencier un employé
 * @method displayEmployesDisponibles() permet d'afficher les employés disponibles
 * @method ingredientsACommanderRapide() permet de commander rapidement des ingrédients
 * @method consulterStock() permet de consulter le stock
 * @method consulterStockPriceToPay() permet de consulter le stock et d'avoir le prix de l'augmentation du stock
 * @method finJournee() permet de finir la journée
 * @method selectTeam() permet de sélectionner une équipe
 * @method setStock() permet de modifier le stock
 * @see Employe est une classe mère des différents employés du restaurant, occupant des postes différents
 */
public class Manager extends Employe {
    private HashMap<String, ArrayList<Employe>> employes;
    private ArrayList<Commande> historique_commandes;

    //region Constructor
    public Manager(String nom, String prenom, int salaire, String poste) {
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
     *
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
    public void finJournee() {
    }

    /**
     * @param stockLow  Valeur minimale dans stock
     * @param stockFull Valeur de stock full
     * @param stock     Le stock à controler
     * @return Le prix total à payer
     * @author Thibaut
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
     * @return La map d'ingrédient commandé avec la quantité
     * @author Thibaut
     */
    public Map<Ingredients, Integer> consulterStock() {
        Stock stock = new Stock();
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
     *
     * @return Le prix de l'augmentation stock
     * @author Thibaut
     */
    public int consulterStockPriceToPay() {
        Map<Ingredients, Integer> ingredientAchetes;
        ingredientAchetes = consulterStock();
        int priceToPay = 0;

        for (Map.Entry<Ingredients, Integer> entry : ingredientAchetes.entrySet()) {
            Ingredients ingredient = entry.getKey();
            priceToPay += ingredient.getPrice() * entry.getValue();
        }

        return priceToPay;
    }

    //endregion

    //region MethodesEmployes

    /**
     * Permet de créer un employé et de l'ajouter à la liste des employés disponibles
     *
     * @author Hippolyte
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
     *
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

    public ArrayList<Employe> getEmployesDisponibles() {

        // on récupère les fichiers contenant les différents employés
        // (un fichier par employé)
        final String dossier = "employes/";
        File dossierEmploye = new File(dossier);

        ArrayList<Employe> employes = new ArrayList<>();

        // on vérifie que le dossier existe et qu'il s'agit bien d'un dossier
        if (dossierEmploye.exists() && dossierEmploye.isDirectory()) {

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
        } else {
            System.err.println("Impossible de lire le dossier : " + dossier);
        }
        return employes;
    }

    /**
     * Permet d'afficher les différents employés disponibles
     *
     * @author Hippolyte
     */
    public void displayEmployesDisponibles() {
        ArrayList<Employe> employes = getEmployesDisponibles();
        // affichage de chaque employé disponible
        if (!employes.isEmpty()) {
            System.out.println("Employés disponibles :");
            for (Employe employe : employes) {
                employe.afficherConcis();
            }
        } else {
            System.out.println("Aucun employé disponible.");
        }
    }

    public Employe selectEmploye(String nom, String prenom, String poste, ArrayList<Employe> employesDispos)
            throws ErrorHandler {
        for (Employe employe : employesDispos) {
            if (employe.getNom().equals(nom) && employe.getPrenom().equals(prenom)
                    && employe.getPoste().equals(poste)) {
                return employe;
            }
        }
        throw new ErrorHandler("Employé non trouvé : " + nom + " " + prenom + " -> " + poste);
    }

    /**
     * Fonction qui nous retourne une équipe remplissant les conditions d'ouverture du restaurant
     *
     * @return ArrayList<Employe> équipe complète
     */
    public ArrayList<Employe> formerEquipe() throws ErrorHandler {
        // L'équipe qu'on va retourner
        ArrayList<Employe> equipe = new ArrayList<>();
        // On récupère les employés disponibles
        ArrayList<Employe> employesDispos = getEmployesDisponibles();

        // on crée trois tableaux différents afin de pouvoir s'y retrouver
        ArrayList<Employe> cuisiniers = new ArrayList<>();
        ArrayList<Employe> serveurs = new ArrayList<>();
        ArrayList<Employe> barmans = new ArrayList<>();

        System.out.println("Il vous faut au moins 4 cuisiniers, 2 serveurs et 1 barman afin d'ouvrir le restaurant.");
        System.out.println("Choisissez vos cuisiniers (au moins 4) :");

        String operation;

        // interface pour constituer l'équipe de cuisinier
        do {
            // affichage interface CLI
            System.out.println("Vous avez actuellement sélectionné " + cuisiniers.size() + " cuisiniers.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Afficher tous les employés");
            System.out.println("2. Sélectionner");
            System.out.print("(0 pour quitter) > ");
            operation = scanner.nextLine();

            // on traite les inputs
            switch (operation) {
                case "1":
                    displayEmployesDisponibles();
                    break;
                case "2":
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    try {
                        // on va récupérer l'employé en question
                        // on vérifie qu'il s'agit bien d'un cuisinier et qu'il existe
                        Employe nouveau = selectEmploye(nom, prenom, "cuisinier", employesDispos);

                        // On vérifie qu'il n'est pas déjà sélectionné
                        for (Employe cuisinier : cuisiniers) {
                            if (cuisinier.getNom().equals(nouveau.getNom()) &&
                                    cuisinier.getPrenom().equals(nouveau.getPrenom())) {
                                throw new ErrorHandler("Cuisinier déjà sélectionné.");
                            }
                        }
                        cuisiniers.add(nouveau);
                    } catch (ErrorHandler e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.err.println("Opération non supportée.");
                    break;
            }
        } while (!operation.equals("0") && cuisiniers.size() < 4);

        System.out.println("Choisissez vos serveurs (au moins 2) :");

        // interface pour constituer l'équipe de serveurs
        do {
            // affichage de l'interface CLI
            System.out.println("Vous avez actuellement sélectionné " + serveurs.size() + " serveurs.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Afficher tous les employés");
            System.out.println("2. Sélectionner");
            System.out.print("(0 pour quitter) > ");
            operation = scanner.nextLine();

            // on traite les inputs
            switch (operation) {
                case "1":
                    displayEmployesDisponibles();
                    break;
                case "2":
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    try {
                        // on va récupérer l'employé en question
                        // on vérifie qu'il s'agit bien d'un serveur et qu'il existe
                        Employe nouveau = selectEmploye(nom, prenom, "serveur", employesDispos);

                        // On vérifie qu'il n'est pas déjà sélectionné
                        for (Employe serveur : serveurs) {
                            if (serveur.getNom().equals(nouveau.getNom()) &&
                                    serveur.getPrenom().equals(nouveau.getPrenom())) {
                                throw new ErrorHandler("Serveur déjà sélectionné.");
                            }
                        }
                        serveurs.add(nouveau);
                    } catch (ErrorHandler e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.err.println("Opération non supportée.");
                    break;

            }
        } while (!operation.equals("0") && serveurs.size() < 2);

        System.out.println("Choisissez votre barman (1) :");

        // interface pour constituer l'équipe de barman
        do {
            // affichage de l'interface CLI
            System.out.println("Vous avez actuellement sélectionné " + barmans.size() + " barman.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Afficher tous les employés");
            System.out.println("2. Sélectionner");
            System.out.print("(0 pour quitter) > ");
            operation = scanner.nextLine();

            // on traite les inputs
            switch (operation) {
                case "1":
                    displayEmployesDisponibles();
                    break;
                case "2":
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    try {
                        // on va récupérer l'employé en question
                        // on vérifie qu'il s'agit bien d'un barman et qu'il existe
                        Employe nouveau = selectEmploye(nom, prenom, "barman", employesDispos);
                        for (Employe barman : barmans) {
                            if (barman.getNom().equals(nouveau.getNom()) &&
                                    barman.getPrenom().equals(nouveau.getPrenom())) {
                                throw new ErrorHandler("Barman déjà sélectionné.");
                            }
                        }
                        barmans.add(nouveau);
                    } catch (ErrorHandler e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.err.println("Opération non supportée.");
                    break;
            }
        } while (!operation.equals("0") && barmans.isEmpty());

        // On vérifie ce que si l'équipe est bien complète, on throw une erreur sinon
        if (barmans.isEmpty() || cuisiniers.size() < 4 || serveurs.size() < 2) {
            throw new ErrorHandler("L'équipe n'est pas complète.");
        }
        // on assemble l'équipe
        equipe.addAll(cuisiniers);
        equipe.addAll(serveurs);
        equipe.addAll(barmans);
        return equipe;
    }
}