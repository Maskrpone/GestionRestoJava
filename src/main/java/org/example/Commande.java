package org.example;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Commande implements Serializable {
    private final int table;
    private final ArrayList<Produits> repas;
    private final ArrayList<Boisson> boissons;
    private boolean enPreparation;

    // region Constructor
    public Commande(int table, ArrayList<Boisson> boissons, ArrayList<Produits> repas) {
        this.table = table;
        this.boissons = boissons;
        this.repas = repas;
        this.enPreparation = false;
    }
    // endregion

    // region getters
    public int getTable() {
        return table;
    }

    public ArrayList<Produits> getRepas() {
        return repas;
    }

    public ArrayList<Boisson> getBoissons() {
        return boissons;
    }
    // endregion

    public void setEnPreparation(boolean b) {
        this.enPreparation = b;
    }

    public void addBoisson(Boisson boisson) {
        this.boissons.add(boisson);
    }

    public void addRepas(Produits repas) {
        this.repas.add(repas);
    }

    public void afficherCommande() {
        // Créer une fenêtre
        JFrame frame = new JFrame("La commande");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Afficher la commande
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(boissons.size() + repas.size() + 5, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Afficher le numéro de la table
        JLabel tableLabel = new JLabel("Table n°" + table);
        tableLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(tableLabel);

        // Ajouter une ligne pour les boissons
        JLabel boissonsLabel = new JLabel("Boissons :");
        boissonsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(boissonsLabel);

        // Afficher les boissons
        for (Boisson boisson : boissons) {
            JLabel boissonLabel = new JLabel(boisson.getName());
            boissonLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(boissonLabel);
        }

        // Ajouter une ligne pour les plats
        JLabel platsLabel = new JLabel("Plats :");
        platsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(platsLabel);

        // Afficher les plats
        for (Produits plat : repas) {
            JLabel platLabel = new JLabel(plat.getNom());
            platLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(platLabel);
        }

        // Ajouter le panneau à la fenêtre
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // Ajouter le bouton de validation de la commande
        JButton buttonCommande = new JButton("Valider la commande");
        buttonCommande.setFont(new Font("Arial", Font.BOLD, 14));
        buttonCommande.setBackground(Color.GREEN);
        buttonCommande.setForeground(Color.WHITE);
        buttonCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur le bouton de validation
                System.out.println("Vous avez validé la commande");

                // Fermer la fenêtre
                frame.dispose();

                // Créer et démarrer les threads pour les actions en parallèle
                Thread cuisinierThread = new Thread(new Runnable() {
                    public void run() {
                        Cuisinier cuisinier = new Cuisinier("Bob", "Leponge", 2, "Cuisinier");
                        cuisinier.cuisinerCommande(Commande.this);
                    }
                });

                Thread barmanThread = new Thread(new Runnable() {
                    public void run() {
                        Barman barman = new Barman("Bob", "Leponge", 2, "Cuisinier");
                        barman.preparerBoisson(Commande.this);
                    }
                });

                Commande.this.setEnPreparation(true);

                cuisinierThread.start();
                barmanThread.start();

                // Dès que les threads sont terminés, afficher la facture
                try {
                    cuisinierThread.join();
                    barmanThread.join();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                Commande.this.setEnPreparation(false);
                afficherFacture();

            }
        });
        panel.add(buttonCommande);
    }

    protected void afficherFacture() {
        // Créer une fenêtre
        JFrame frame = new JFrame("La facture");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Afficher la facture
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(boissons.size() + repas.size() + 10, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Afficher le numéro de la table
        JLabel tableLabel = new JLabel("Table n°" + table);
        tableLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(tableLabel);

        // Ajouter une ligne pour les boissons
        JLabel boissonsLabel = new JLabel("Boissons :");
        boissonsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(boissonsLabel);

        // Afficher les boissons
        for (Boisson boisson : boissons) {
            JLabel boissonLabel = new JLabel(boisson.getName() + " - " + boisson.getPrice() + " euros");
            boissonLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(boissonLabel);
        }

        // Ajouter une ligne pour les plats
        JLabel platsLabel = new JLabel("Plats :");
        platsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(platsLabel);

        // Afficher les plats
        for (Produits plat : repas) {
            JLabel platLabel = new JLabel(plat.getNom() + " - " + plat.getPrice() + " euros");
            platLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(platLabel);
        }

        // afficher le prix total
        JLabel prixTotalLabel = new JLabel("Prix total : " + getPrixTotal() + " euros");
        prixTotalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(prixTotalLabel);

        // Ajouter le panneau à la fenêtre
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // Ajouter le bouton pour payer (en une ou plusieurs fois)
        JButton buttonCommande = new JButton("Payer");
        buttonCommande.setFont(new Font("Arial", Font.BOLD, 14));
        buttonCommande.setBackground(Color.GREEN);
        buttonCommande.setForeground(Color.WHITE);
        buttonCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // nouvelle fenetre pour payer
                JFrame frame = new JFrame("Payer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Créer un panneau
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 2));

                // Ajouter des boutons pour chaque écrans
                JButton button = new JButton("Payer en une fois");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Réagir au clic sur la boisson
                        System.out.println("Vous avez choisi de payer en une fois");

                        merci();
                        // Fermer la fenêtre
                        frame.dispose();
                    }
                });
                panel.add(button);

                button = new JButton("Payer en plusieurs fois");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Réagir au clic sur la boisson
                        System.out.println("Vous avez choisi de payer en plusieurs fois");

                        merci();
                        // Fermer la fenêtre
                        frame.dispose();
                    }
                });
                panel.add(button);

                // Ajouter le panneau à la fenêtre
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
        panel.add(buttonCommande);

        // Ajouter le bouton pour imprimer
        JButton buttonPrint = new JButton("Imprimer");
        buttonPrint.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPrint.setBackground(Color.GREEN);
        buttonPrint.setForeground(Color.WHITE);
        buttonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur le bouton de validation
                System.out.println("Vous avez imprimé la facture");
            }
        });
        panel.add(buttonPrint);

        // Ajouter le bouton pour fermer
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.setBackground(Color.RED);
        buttonFermer.setForeground(Color.WHITE);
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur le bouton de validation
                System.out.println("Vous avez fermé la facture");

                // Fermer la fenêtre
                frame.dispose();
            }
        });
        panel.add(buttonFermer);

    }

    private String getPrixTotal() {
        int prixTotal = 0;
        for (Boisson boisson : boissons) {
            prixTotal += boisson.getPrice();
        }
        for (Produits plat : repas) {
            prixTotal += plat.getPrice();
        }
        return String.valueOf(prixTotal);
    }

    private void merci() {
        // Afficher le message de remerciement
        JFrame frame = new JFrame("Merci !");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Ajouter des boutons pour chaque écrans
        JLabel label = new JLabel("Merci d'avoir choisi notre restaurant !");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);

        // bouton pour fermer
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.setBackground(Color.RED);
        buttonFermer.setForeground(Color.WHITE);
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre
                frame.dispose();
            }
        });
        panel.add(buttonFermer);
        

        // Ajouter le panneau à la fenêtre
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}