package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Menu {
    private ArrayList<Produits> cartePlat;
    private ArrayList<Boisson> carteBoisson;

    // region Constructor

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
                    platData.getInt("preparation"));

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

    // endregion

    // region Getter

    public ArrayList<Produits> getCartePlat() {
        return cartePlat;
    }

    public ArrayList<Boisson> getCarteBoisson() {
        return carteBoisson;
    }
    // endregion

    // region Methode

    public void afficherMenu() {

        Menu menu = this;
        Commande commande = new Commande(12, new ArrayList<>(), new ArrayList<>());

        // Créer une fenêtre
        JFrame frame = new JFrame("Le Menu !");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(menu.getCartePlat().size() + menu.getCarteBoisson().size() + 9, 1));
        panel.setBackground(Color.WHITE);

        // Ajouter un titre
        JLabel titleLabel = new JLabel("Le Menu !");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        // Ajouter une séparation entre les plats et les boissons
        JLabel separationLabel = new JLabel("Plats");
        separationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        separationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(separationLabel);

        // Ajouter des boutons pour chaque plat
        for (Produits plat : menu.getCartePlat()) {
            JButton button = new JButton(plat.getNom() + " - " + plat.getPrice() + " euros");
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Réagir au clic sur le plat
                    System.out.println("Vous avez choisi le plat : " + plat.getNom());

                    // Ajouter le plat à la commande
                    commande.addRepas(plat);
                }
            });
            panel.add(button);
        }

        // Ajouter une séparation entre les plats et les boissons
        separationLabel = new JLabel("Boissons");
        separationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        separationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(separationLabel);

        // Ajouter des boutons pour chaque boisson
        for (Boisson boisson : menu.getCarteBoisson()) {
            JButton button = new JButton(boisson.getName() + " - " + boisson.getPrice() + " euros");
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Réagir au clic sur la boisson
                    System.out.println("Vous avez choisi la boisson : " + boisson.getName());

                    // Ajouter la boisson à la commande
                    commande.addBoisson(boisson);
                }
            });
            panel.add(button);
        }

        // Ajouter une séparation entre les plats et les boissons
        separationLabel = new JLabel(" ");
        separationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        separationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(separationLabel);

        // Voir la commande
        JButton buttonCommande = new JButton("Voir la commande");
        buttonCommande.setFont(new Font("Arial", Font.BOLD, 16));
        buttonCommande.setBackground(Color.GREEN);
        buttonCommande.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi de voir la commande");

                // Afficher la commande
                commande.afficherCommande();
            }
        });
        panel.add(buttonCommande);

        JButton button = new JButton("FERMER");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.RED);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi de fermer le menu");

                // Fermer la fenêtre
                frame.dispose();
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(400, 800);

        // Rendre la fenêtre visible
        frame.setVisible(true);

        // endregion
    }

    // ...
}
