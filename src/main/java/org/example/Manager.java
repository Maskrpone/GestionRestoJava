package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
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
     * Permet au manger de consulter les stocks des ingrédients et de les augmenter
     * @return La map d'ingrédient commandé avec la quantité
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
     * @return Le prix de l'augmentation stock
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

    /**
     * Permet au manger de consulter les stocks des boissons et de les augmenter
     * @return La map d'ingrédient commandé avec la quantité
     */
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
                    JOptionPane.showMessageDialog(frame, "Veuillez saisir le nom et le prénom de l'employé.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer le fichier de l'employé
                String fichierEmploye = "employes/" + nomString + "_" + prenomString + ".ser";
                File employe = new File(fichierEmploye);

                // Si le fichier existe, on le supprime
                if (employe.exists()) {
                    if (employe.delete()) {
                        JOptionPane.showMessageDialog(frame, "L'employé a été licencié.", "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Erreur lors de la suppression du fichier : " + fichierEmploye, "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Cet employé n'existe pas.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
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
     */
    public static void formerEquipe() {

        // System.out.println("Rappel : il vous faut au moins 4 cuisiniers, 2 serveurs
        // et 1 barman afin d'ouvrir le restaurant.");

        ArrayList<Employe> equipe = new ArrayList<>();
        ArrayList<Employe> employesDispos = getEmployesDisponibles();
        String operation;

        // afficher les employés dispo sous forme de bouton et au clic, les ajouter à
        // l'équipe

        JFrame frame = new JFrame("Employés disponibles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(employesDispos.size() + 3, 1));

        for (Employe employe : employesDispos) {

            JButton button = new JButton(employe.getNom() + " " + employe.getPrenom() + " - " + employe.getPoste());
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Réagir au clic sur l'employé
                    System.out.println("Vous avez choisi l'employé : " + employe.getNom() + " " + employe.getPrenom()
                            + " - " + employe.getPoste());

                    // Ajouter l'employé à l'équipe
                    equipe.add(employe);

                    // Supprimer l'employé des employés disponibles
                    employesDispos.remove(employe);

                    // Supprimer le bouton de l'employé
                    panel.remove(button);
                    panel.revalidate();
                    panel.repaint();

                }
            });
            panel.add(button);
        }

        // bouton de séparation
        JButton button = new JButton(" ");
        button.setEnabled(false);
        panel.add(button);

        JButton voirEquipeButton = new JButton("Voir l'équipe");
        voirEquipeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        voirEquipeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        voirEquipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur le bouton Voir l'équipe
                JFrame frame = new JFrame("Equipe");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(equipe.size() + 1, 1));

                for (Employe employe : equipe) {
                    JLabel label = new JLabel(
                            employe.getNom() + " " + employe.getPrenom() + " - " + employe.getPoste());
                    label.setFont(new Font("Arial", Font.PLAIN, 14));
                    label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                    panel.add(label);
                }

                JButton validerButton = new JButton("Fermer");
                validerButton.setFont(new Font("Arial", Font.PLAIN, 14));
                validerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                validerButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Fermer la fenêtre
                        frame.dispose();
                    }
                });

                panel.add(validerButton);

                frame.getContentPane().add(panel);
                frame.setSize(500, 350);
                frame.setVisible(true);

            }
        });

        panel.add(voirEquipeButton);

        JButton validerButton = new JButton("Valider");
        validerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        validerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!verifEquipe(equipe)) {
                    JOptionPane.showMessageDialog(frame,
                            "Il vous faut au moins 4 cuisiniers, 2 serveurs et 1 barman afin d'ouvrir le restaurant.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("Vous avez choisi de valider l'équipe :");
                for (Employe employe : equipe) {
                    System.out.println(employe.getNom() + " " + employe.getPrenom() + " - " + employe.getPoste());
                }

                // Fermer la fenêtre
                frame.dispose();
            }
        });

        panel.add(validerButton);

        frame.getContentPane().add(panel);
        frame.setSize(500, 350);
        frame.setVisible(true);

    }

    /**
     * Fonction vérifiant que l'équipe est bien composée de au moins 4 cuisiniers, 2 serveurs 1 barman
     * @param equipe Equipe à vérifier
     * @return Equipe valide ou non
     */
    public static boolean verifEquipe(ArrayList<Employe> equipe) {
        // vérifier qu'il y a au moins 4 cuisiniers, 2 serveurs et 1 barman
        int nbCuisiniers = 0;
        int nbServeurs = 0;
        int nbBarmans = 0;

        for (Employe employe : equipe) {
            if (employe.getPoste().equals("cuisinier")) {
                nbCuisiniers++;
            } else if (employe.getPoste().equals("serveur")) {
                nbServeurs++;
            } else if (employe.getPoste().equals("barman")) {
                nbBarmans++;
            }
        }
        if (nbCuisiniers < 4 || nbServeurs < 2 || nbBarmans < 1) {
            return false;
        }
        return true;
    }

    /**
     * Affiche le résultat de la journée du restaurant
     */
    public static void resultatTotal() {
        JFrame frame = new JFrame("Résultat de la journée");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        // On crée un tableau
        String[] columnNames = { "Commande", "Prix" };
        Object[][] data;
        int totalDay = 0;

        // On récupère les commandes du jour
        File dossierCommandes = new File("facture/additionClient/");
        File[] fichiersCommandes = dossierCommandes.listFiles();
        if (fichiersCommandes != null) {
            data = new Object[fichiersCommandes.length][2];
            for (int i = 0; i < fichiersCommandes.length; i++) {
                File commande = fichiersCommandes[i];

                // On récupère le nom de la commande
                String commandeName = commande.getName();
                data[i][0] = commandeName;

                // On récupère le prix de la commande
                int total = getTotalFromCommande(commande);
                data[i][1] = total;
                totalDay += total;
            }

            // On crée un modèle de tableau
            DefaultTableModel model = new DefaultTableModel(data, columnNames);

            // on mets en entete le total de la journée
            model.setColumnIdentifiers(new String[] { "Commande", "Prix (Total : " + totalDay + ")" });

            // On crée un tableau avec le modèle
            JTable table = new JTable(model);

            // On ajoute le tableau au panneau
            panel.add(new JScrollPane(table));
        } else {
            data = new Object[0][0];
        }

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    public static void resultatJournee() {
        JFrame frame = new JFrame("Résultat de la journée");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        // On crée un tableau
        String[] columnNames = { "Commande", "Prix" };
        Object[][] data;
        int totalDay = 0;

        String lastCommandDate = getLastCommandDate("facture/additionClient/");

        // On récupère les commandes du jour
        File dossierCommandes = new File("facture/additionClient/");
        File[] fichiersCommandes = dossierCommandes.listFiles();
        if (fichiersCommandes != null) {
            data = new Object[fichiersCommandes.length][2];
            for (int i = 0; i < fichiersCommandes.length; i++) {
                File commande = fichiersCommandes[i];

                // On récupère le nom de la commande
                String commandeName = commande.getName();

                // On extrait la date du nom de la commande
                String commandeDate = extractDateFromCommandeName(commandeName);

                // Comparez la date avec la date de la dernière commande
                if (commandeDate.equals(lastCommandDate)) {
                    data[i][0] = commandeName;

                    // On récupère le prix de la commande
                    int total = getTotalFromCommande(commande);
                    data[i][1] = total;
                    totalDay += total;
                }
            }

            // mettre a jour la taille de data
            int nbCommandes = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i][0] != null) {
                    nbCommandes++;
                }
            }
            Object[][] data2 = new Object[nbCommandes][2];

            int index = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i][0] != null) {
                    data2[index][0] = data[i][0];
                    data2[index][1] = data[i][1];
                    index++;
                }
            }

            // On crée un modèle de tableau
            DefaultTableModel model = new DefaultTableModel(data2, columnNames);

            // on mets en entete le total de la journée
            model.setColumnIdentifiers(new String[] { "Commande", "Prix (Total : " + totalDay + ")" });

            // On crée un tableau avec le modèle
            JTable table = new JTable(model);

            // On ajoute le tableau au panneau
            panel.add(new JScrollPane(table));
        } else {
            data = new Object[0][0];
        }

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    /**
     * Fonction qui récupère le nom du dernier fichier
     * @param string Nom du fichier à trouver
     * @return Nom du fichier trouvé
     */
    private static String getLastCommandDate(String string) {
        // récupérer le nom du dernier fichier du dossier facture/additionClient
        File dossierCommandes = new File(string);
        File[] fichiersCommandes = dossierCommandes.listFiles();

        if (fichiersCommandes != null) {
            File lastCommande = fichiersCommandes[fichiersCommandes.length - 1];
            String lastCommandeName = lastCommande.getName();

            // extraire la date du nom du fichier
            return extractDateFromCommandeName(lastCommandeName);
        } else {
            return "";
        }
    }

    /**
     * Fonction qui permet d'avoir le total du prix d'une commande
     * @param commande commande dont on cherche le total
     * @return Prix total de la commande
     */
    private static int getTotalFromCommande(File commande) {
        int total = 0;
        try (BufferedReader lecteur = new BufferedReader(new FileReader(commande))) {
            String ligne;
            boolean sectionTotal = false;

            while ((ligne = lecteur.readLine()) != null) {
                // System.out.println("Ligne du fichier : [" + ligne + "] - Longueur : " +
                // ligne.length());

                Pattern pattern = Pattern.compile("Total:\\s*(\\d+)");
                Matcher matcher = pattern.matcher(ligne.trim());

                if (matcher.matches()) {
                    String totalStr = matcher.group(1);
                    try {
                        total = Integer.parseInt(totalStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de conversion en entier : " + e.getMessage());
                    }
                    sectionTotal = true;
                    break;
                }

                if (sectionTotal) {
                    try {
                        total = Integer.parseInt(ligne.trim().substring("Total:".length()));
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de conversion en entier : " + e.getMessage());
                    }
                    break; // La section Total est terminée, sortir de la boucle
                }
            }

        } catch (IOException e) {
            System.err.println("Impossible de lire : " + e);
        }
        return total;
    }

    /**
     * Méthode pour extraire la date du nom de la commande
     */
    private static String extractDateFromCommandeName(String commandeName) {
        // Votre logique d'extraction de date ici
        // Par exemple, si le format est toujours le même, vous pouvez utiliser
        // substring
        return commandeName.substring(18, 26); // Assurez-vous d'ajuster les indices selon votre format réel
    }
}