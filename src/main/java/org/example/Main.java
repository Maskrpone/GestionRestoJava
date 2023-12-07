package org.example;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        // System.out.println(menu.getCartePlat().get(2));
        // System.out.println(menu.getCartePlat().get(5));
        // System.out.println(menu.getCartePlat().get(6));

        /*
         * Ingredients i = new Ingredients("champignon", 1, 20);
         * i.ecritureFichier(true);
         * Ingredients i1 = new Ingredients("fromage", 2, 20);
         * i1.ecritureFichier(true);
         * Ingredients i2 = new Ingredients("oignon", 1, 20);
         * i2.ecritureFichier(true);
         * Ingredients i3 = new Ingredients("pain", 1, 20);
         * i3.ecritureFichier(true);
         * Ingredients i4 = new Ingredients("patate", 1, 20);
         * i4.ecritureFichier(true);
         * Ingredients i5 = new Ingredients("salade", 1, 20);
         * i5.ecritureFichier(true);
         * Ingredients i6 = new Ingredients("saucisse", 2, 20);
         * i6.ecritureFichier(true);
         * Ingredients i7 = new Ingredients("steak", 3, 20);
         * i7.ecritureFichier(true);
         * Ingredients i8 = new Ingredients("tomate", 1, 20);
         * i8.ecritureFichier(true);
         */

        /*
         * Boisson b = new Boisson("limonade", 4, 20);
         * Boisson b1 = new Boisson("cidre", 5, 20);
         * Boisson b2 = new Boisson("biere", 5, 20);
         * Boisson b3 = new Boisson("jus", 1, 20);
         * Boisson b4 = new Boisson("eau", 0, 20);
         * b.ecritureFichier(true);
         * b1.ecritureFichier(true);
         * b2.ecritureFichier(true);
         * b3.ecritureFichier(true);
         * b4.ecritureFichier(true);
         * 
         */

        /*
         * HashMap<String, Integer> ingredients = new HashMap<>();
         * ingredients.put("pain", 1);
         * ingredients.put("tomate", 1);
         * ingredients.put("salade", 1);
         * ingredients.put("steak", 1);
         * Produits burger = new Produits("Hamburger", 10, ingredients, 1);
         * 
         * ArrayList<String> ingredients2 = new ArrayList<>();
         * ingredients2.add("limonade");
         * Boisson limonade = new Boisson("Limonade", 5);
         * 
         * ArrayList<Produits> repas = new ArrayList<>();
         * repas.add(burger);
         * ArrayList<Boisson> boissons = new ArrayList<>();
         * boissons.add(limonade);
         * 
         * Serveur serveur;
         * //serveur.addCommande(15,boissons, repas);
         * 
         */

        // TODO: gérer l'exception lorsque les fichier .ser n'existent pas
        // TODO: faire en sorte d'avoir un stock par défaut (fonction qui init le stock
        // pour le manager par ex)

        // ArrayList<HashMap<Produits, Integer>> produits;
        // ArrayList<Produits> plat = new ArrayList<>();
        // ArrayList<Boisson> boisson = new ArrayList<>();

        // plat.add(menu.getCartePlat().get(2));
        // plat.add(menu.getCartePlat().get(5));
        // plat.add(menu.getCartePlat().get(6));
        // boisson.add(menu.getCarteBoisson().get(1));
        // boisson.add(menu.getCarteBoisson().get(2));
        // boisson.add(menu.getCarteBoisson().get(3));
        // System.out.println(menu.getCarteBoisson().get(1));
        // System.out.println(menu.getCarteBoisson().get(2));
        // System.out.println(menu.getCarteBoisson().get(3));
        // Commande commande = new Commande(12, boisson, plat);
        // Cuisinier cuisinier = new Cuisinier("Bob", "Leponge", 2, "Cuisinier");
        // Barman barman = new Barman("Bob", "Leponge", 2, "Cuisinier");
        // //cuisinier.cuisinerCommande(commande);
        // //barman.preparerBoisson(commande);
        // menu.afficherMenu();


        // Créer une fenêtre
        JFrame frame = new JFrame("Gestion du restaurant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout( 3, 2));


        // Ajouter des boutons pour chaque écrans
        JButton button = new JButton("Serveur (Prise de commande)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Serveur");
                menu.afficherMenu();
            }
        });
        panel.add(button);

        button = new JButton("Barman (Préparation des boissons)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Barman");
            }
        });
        panel.add(button);

        button = new JButton("Cuisinier (Préparation des plats)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Cuisinier");
            }
        });
        panel.add(button);

        button = new JButton("Manager (Perfomance du restaurant / Courses)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Manager");
            }
        });
        panel.add(button);


        button = new JButton("Administrateur (Gestion des employés et stock)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Administrateur");
            }
        });
        panel.add(button);


        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(800, 550);

        // Rendre la fenêtre visible
        frame.setVisible(true);


    }
}
