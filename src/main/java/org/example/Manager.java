package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Hippolyte & Thibaut
 * @class Manager est une classe fille de Employe
 * @method embaucher() permet d'embaucher un employé
 * @method licencier() permet de licencier un employé
 * @method displayEmployesDisponibles() permet d'afficher les employés
 *         disponibles
 * @method ingredientsACommanderRapide() permet de commander rapidement des
 *         ingrédients
 * @method consulterStock() permet de consulter le stock
 * @method consulterStockPriceToPay() permet de consulter le stock et d'avoir le
 *         prix de l'augmentation du stock
 * @method finJournee() permet de finir la journée
 * @method selectTeam() permet de sélectionner une équipe
 * @method setStock() permet de modifier le stock
 * @see Employe est une classe mère des différents employés du restaurant,
 *      occupant des postes différents
 */
public class Manager extends Employe {
    private HashMap<String, ArrayList<Employe>> employes;
    private ArrayList<Commande> historique_commandes;

    // region Constructor
    public Manager(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
        this.employes = new HashMap<>();
    }
    // endregion

    // region Getter
    public HashMap<String, ArrayList<Employe>> getEmployes() {
        return employes;
    }

    public ArrayList<Commande> getHistorique_commandes() {
        return historique_commandes;
    }
    // endregion

    // region Setter

    public void setEmployes(HashMap<String, ArrayList<Employe>> employes) {
        this.employes = employes;
    }

    /**
     * Permet de sélectionner une équipe
     *
     * @return le hashmap d'une équipe qu'on chargera dans l'attribut employé du
     *         resto
     */
    public HashMap<String, Employe> selectTeam() {
        return null;
    }

    public void setStock(Stock stock) {

    }

    public void setHistorique_commandes(ArrayList<Commande> historique_commandes) {
        this.historique_commandes = historique_commandes;
    }

    // endregion

    // region MethodesStock
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
    public static Map<Ingredients, Integer> consulterStock() {
        Stock stock = new Stock();
        ArrayList<Ingredients> currentStock = stock.getStock();
        Map<Ingredients, Integer> quantitiesAdded = new HashMap<>();

        // On affiche le stock actuel dans une JFrame
        JFrame frame = new JFrame("Stock actuel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // On crée un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        // On crée un tableau
        String[] columnNames = { "Nom", "Quantité" };
        String[][] data = new String[currentStock.size()][2];
        int index = 0;
        for (Ingredients ingredient : currentStock) {
            data[index][0] = ingredient.getNom();
            data[index][1] = Integer.toString(ingredient.getNb());
            index++;
        }
        JTable tableau = new JTable(data, columnNames);
        tableau.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // On ajoute le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // On définit la taille de la fenêtre
        frame.setSize(500, 350);

        // On rend la fenêtre visible
        frame.setVisible(true);

        return quantitiesAdded;
    }

    /**
     * Utilise la fonction consulterStock et permet d'avoir le prix au lieu d'une
     * map
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

    public static Map<Boisson, Integer> consulterStockBoisson() {
        Stock stock = new Stock();
        ArrayList<Boisson> currentStock = stock.getStockBoisson();
        Map<Boisson, Integer> quantitiesAdded = new HashMap<>();

        // On affiche le stock actuel dans une JFrame
        JFrame frame = new JFrame("Stock actuel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // On crée un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        // On crée un tableau
        String[] columnNames = { "Nom", "Quantité" };
        String[][] data = new String[currentStock.size()][2];
        int index = 0;
        for (Boisson boisson : currentStock) {
            data[index][0] = boisson.getName();
            data[index][1] = Integer.toString(boisson.getNb());
            index++;
        }
        JTable tableau = new JTable(data, columnNames);
        tableau.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // On ajoute le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // On définit la taille de la fenêtre
        frame.setSize(500, 350);

        // On rend la fenêtre visible
        frame.setVisible(true);

        return quantitiesAdded;
    }

    // endregion

    // region MethodesEmployes

    /**
     * Permet de récupérer les employes à partir d'un fichier json contenant des
     * employes par défaut afin d'initialiser les fichiers serialies plus facilement
     */
    public void jsonEmployesToSerialize() {
        try {
            // Lire le fichier JSON en tant que chaîne
            String employeJson = new String(Files.readAllBytes(Paths.get("employe.json")));

            // Créer un objet JSON à partir de la chaîne
            JSONObject jsonObject = new JSONObject(employeJson);

            // Obtenir le tableau d'employés à partir de la clé "employes"
            JSONArray employesArray = jsonObject.getJSONArray("employe");

            // Parcourir les objets du tableau
            for (int i = 0; i < employesArray.length(); i++) {
                JSONObject employeData = employesArray.getJSONObject(i);
                String nom = employeData.getString("nom");
                String prenom = employeData.getString("prenom");
                int salaire = employeData.getInt("salaire");
                int joursTravailles = employeData.getInt("jours_travailles");
                String poste = employeData.getString("poste");

                Employe employe = new Employe(nom, prenom, salaire, joursTravailles, poste);

                employe.ecrireEmploye();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Permet de créer un employé et de l'ajouter à la liste des employés
     * disponibles
     *
     * @author Hippolyte
     */
    public static void embaucher() {
        JFrame frame = new JFrame("Création d'un employé");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JTextField nom = new JTextField();
        panel.add(new JLabel("Nom:"));
        panel.add(nom);

        JTextField prenom = new JTextField();
        panel.add(new JLabel("Prénom:"));
        panel.add(prenom);

        JTextField salaire = new JTextField();
        panel.add(new JLabel("Salaire:"));
        panel.add(salaire);

        JTextField poste = new JTextField();
        panel.add(new JLabel("Poste:"));
        panel.add(poste);

        JButton validerButton = new JButton("Valider");
        panel.add(validerButton);

        frame.getContentPane().add(panel);
        frame.setSize(500, 350);
        frame.setVisible(true);

        // Ajouter un écouteur d'événements sur le bouton Valider
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les informations après que l'utilisateur a cliqué sur Valider
                String nomString = nom.getText();
                String prenomString = prenom.getText();

                try {
                    int salaireInt = Integer.parseInt(salaire.getText());
                    String posteString = poste.getText();

                    Employe employe = new Employe(nomString, prenomString, salaireInt, posteString);
                    employe.ecrireEmploye();

                    // Fermer la fenêtre après avoir ajouté l'employé
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Le salaire doit être un nombre entier.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Permet de licencier un employé et de supprimer son profil du répertoire
     * "employes/" s'il existe
     *
     * @author Hippolyte
     */
    public static void licencier() {
        JFrame frame = new JFrame("Licencier un employé");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
    
        JTextField nom = new JTextField();
        panel.add(new JLabel("Nom:"));
        panel.add(nom);
    
        JTextField prenom = new JTextField();
        panel.add(new JLabel("Prénom:"));
        panel.add(prenom);
    
        JButton licencierButton = new JButton("Licencier");
        panel.add(new JLabel()); // Espace vide pour l'esthétique
        panel.add(licencierButton);
    
        frame.getContentPane().add(panel);
        frame.setSize(500, 350);
        frame.setVisible(true);
    
        // Ajouter un écouteur d'événements sur le bouton Licencier
        licencierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les informations après que l'utilisateur a cliqué sur Licencier
                String nomString = nom.getText();
                String prenomString = prenom.getText();
    
                // Vérifier si les champs sont vides
                if (nomString.isEmpty() || prenomString.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez saisir le nom et le prénom de l'employé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Récupérer le fichier de l'employé
                String fichierEmploye = "employes/" + nomString + "_" + prenomString + ".ser";
                File employe = new File(fichierEmploye);
    
                // Si le fichier existe, on le supprime
                if (employe.exists()) {
                    if (employe.delete()) {
                        JOptionPane.showMessageDialog(frame, "L'employé a été licencié.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression du fichier : " + fichierEmploye, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Cet employé n'existe pas.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
    
                // Fermer la fenêtre après avoir licencié l'employé
                frame.dispose();
            }
        });
    }
    

    public static ArrayList<Employe> getEmployesDisponibles() {

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
    public static void displayEmployesDisponibles() {
        ArrayList<Employe> employes = getEmployesDisponibles();
        // affichage de chaque employé disponible
        if (!employes.isEmpty()) {

            JFrame frame = new JFrame("Employés disponibles");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 1));

            String[] columnNames = { "Nom", "Prénom", "Salaire", "Poste" };
            String[][] data = new String[employes.size()][4];
            int index = 0;
            for (Employe employe : employes) {
                data[index][0] = employe.getNom();
                data[index][1] = employe.getPrenom();
                data[index][2] = Integer.toString(employe.getSalaire());
                data[index][3] = employe.getPoste();
                index++;
            }
            JTable tableau = new JTable(data, columnNames);
            tableau.setFont(new Font("Serif", Font.PLAIN, 20));
            JScrollPane scrollPane = new JScrollPane(tableau);
            panel.add(scrollPane);

            frame.getContentPane().add(panel);
            frame.setSize(500, 350);
            frame.setVisible(true);

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
     * Renvoie une équipe d'employés en fonction du poste
     * (cuisinier, serveur ou barman)
     * La liste d employés renvoyée est ensuite convertie pour devenir la classe que
     * l'on souhaite
     * 
     * @param poste le nom du poste de l'équipe qu'on veut former
     * @return une équipe d'employés
     */
    public ArrayList<Employe> formerEquipe(String poste) {
        System.out.println("Interface de sélection des " + poste + "s :");
        System.out.println(
                "Rappel : il vous faut au moins 4 cuisiniers, 2 serveurs et 1 barman afin d'ouvrir le restaurant.");

        ArrayList<Employe> equipe = new ArrayList<>();
        ArrayList<Employe> employesDispos = getEmployesDisponibles();
        String operation;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("Vous avez actuellement sélectionné " + equipe.size() + " " + poste + "s.");
                System.out.println("1. Afficher tous les employés");
                System.out.println("2. Sélectionner un employé");
                System.out.println("3. pour quitter");
                System.out.print("> ");
                operation = sc.nextLine();

                switch (operation) {
                    case "1":
                        displayEmployesDisponibles();
                        break;
                    case "2":
                        System.out.print("Nom : ");
                        String nom = sc.nextLine();
                        System.out.print("Prénom : ");
                        String prenom = sc.nextLine();
                        try {
                            // on va récupérer l'employé en question
                            // on vérifie qu'il s'agit bien d'un cuisinier et qu'il existe
                            Employe nouveau = selectEmploye(nom, prenom, poste, employesDispos);

                            // On vérifie qu'il n'est pas déjà sélectionné
                            for (Employe employe : equipe) {
                                if (employe.getNom().equals(nouveau.getNom()) &&
                                        employe.getPrenom().equals(nouveau.getPrenom())) {
                                    throw new ErrorHandler(poste + " déjà sélectionné.");
                                }
                            }
                            equipe.add(nouveau);
                        } catch (ErrorHandler e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case "3":
                        break;
                    default:
                        System.err.println("Opération non supportée.");
                        break;
                }
            } while (!operation.equals("3"));
        }

        return null;
    }

    @Override
    public void interfaceEmploye() {
        System.out.println("Vous êtes sur l'écran de gestion des employés : ");
        try (Scanner scanner = new Scanner(System.in)) {
            String operation;

            do {
                System.out.println("1. Afficher tous les employés");
                System.out.println("2. Embaucher");
                System.out.println("3. Licencier");
                System.out.println("4. Retour");
                System.out.print("> ");
                operation = scanner.nextLine();

                switch (operation) {
                    case "1":
                        displayEmployesDisponibles();
                        break;
                    case "2":
                        embaucher();
                        break;
                    case "3":
                        licencier();
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("Opération non supportée.");
                        break;
                }
            } while (!operation.equals("4"));
        }
    }
}