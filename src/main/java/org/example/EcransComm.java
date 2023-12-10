package org.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EcransComm {

    /**
     * Ecran Barman avec les commandes
     */
    public static void affichageCommandeBar() {

        // Modèle de tableau pour stocker les données
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Commande BAR");

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(1235)) {
                    System.out.println("Serveur en attente de connexion...");

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("Connexion acceptée : " + socket);

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        Commande commande = (Commande) inputStream.readObject();
                        System.out.println("Commande reçue : " + commande);

                        // Ajouter la commande au modèle du tableau
                        tableModel.addRow(new Object[] { commande.getBoissonsAffi() });
                    }
                } catch (EOFException e) {
                    // Fin du flux atteinte
                    e.printStackTrace();
                } catch (SocketException e) {
                    // Connexion fermée par l'hôte distant
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();

        // Interface graphique
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Commandes");
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Tableau pour afficher les commandes
        JTable tableau = new JTable();
        tableau.setModel(tableModel);

        // Ajouter le tableau à un JScrollPane pour permettre le défilement si
        // nécessaire
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // Bouton pour fermer l'application
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre
                fenetre.dispose();
            }
        });
        panel.add(buttonFermer);

        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
    }

    /**
     * Affichage écran cuisinier
     */
    public static void affichageCommandeCuisine() {

        // Modèle de tableau pour stocker les données
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Commande CUISINE");

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(1236)) {
                    System.out.println("Serveur en attente de connexion...");

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("Connexion acceptée : " + socket);

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        Commande commande = (Commande) inputStream.readObject();
                        System.out.println("Commande reçue : " + commande);

                        // Ajouter la commande au modèle du tableau
                        tableModel.addRow(new Object[] { commande.getRepasAffi() });
                    }
                } catch (EOFException e) {
                    // Fin du flux atteinte
                    e.printStackTrace();
                } catch (SocketException e) {
                    // Connexion fermée par l'hôte distant
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();

        // Interface graphique
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Commandes");
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Tableau pour afficher les commandes
        JTable tableau = new JTable();
        tableau.setModel(tableModel);

        // Ajouter le tableau à un JScrollPane pour permettre le défilement si
        // nécessaire
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // Bouton pour fermer l'application
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre
                fenetre.dispose();
            }
        });
        panel.add(buttonFermer);

        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
    }

    /**
     * Affichage écran administrateur gestion des stocks
     */
    public static void affichageAdmin() {
        // Créer une fenêtre
        JFrame frame = new JFrame("ADMINISTRATION");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Ajouter des boutons pour chaque écrans
        JButton button = new JButton("Gestions des employés");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi la gestion des employés");
                affichageAdminEmployes();
            }
        });
        panel.add(button);

        button = new JButton("Gestion des stocks");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi la gestion des stocks");
                affichageStock();
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);

    }

    /**
     * Affichage écran gestion des employés
     */
    public static void affichageAdminEmployes() {
        // Créer une fenêtre
        JFrame frame = new JFrame("ADMINISTRATION");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));


        JButton button = new JButton("Liste des employés");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi la liste des employés");
                Manager.displayEmployesDisponibles();
            }
        });
        panel.add(button);

        button = new JButton("Embaucher un employé");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi l'embauche d'un employé");
                Manager.embaucher();
            }
        });
        panel.add(button);

        button = new JButton("Licencier un employé");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi le licenciement d'un employé");
                Manager.licencier();
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);

    }

    /**
     * Affichage écran stocks
     */
    public static void affichageStock(){
        // Créer une fenêtre
        JFrame frame = new JFrame("Admin Stock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Ajouter des boutons pour chaque écrans
        JButton button = new JButton("Stock Ingrédients");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi les ingrédients");
                Manager.consulterStock();
            }
        });
        panel.add(button);

        button = new JButton("Stock Boissons");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi les boissons");
                Manager.consulterStockBoisson();
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);

    }

    /**
     * Affichage écran Manager
     */
    public static void affichageManager() {
        // Créer une fenêtre
        JFrame frame = new JFrame("Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Ajouter des boutons pour chaque écrans
        JButton button = new JButton("Manager Equipe");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi l'equipe");
                Manager.formerEquipe();
            }
        });
        panel.add(button);

        button = new JButton("Manager Résultat");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi les résultat");
                // Manager.resultatJournee();

                JFrame fenetre = new JFrame();
                fenetre.setTitle("Résultats");
                fenetre.setSize(500, 500);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 1));

                // Tableau pour afficher les commandes
                JButton button = new JButton("Résultats de la journée");

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Réagir au clic sur la boisson
                        System.out.println("Vous avez choisi les résultats de la journée");
                        Manager.resultatJournee();
                        fenetre.dispose();
                    }
                });

                panel.add(button);

                JButton button2 = new JButton("Résultats totaux");
                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Réagir au clic sur la boisson
                        System.out.println("Vous avez choisi les résultats totaux");
                        Manager.resultatTotal();
                        fenetre.dispose();
                    }
                });

                panel.add(button2);
                fenetre.setContentPane(panel);
                fenetre.setVisible(true);
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(500, 350);

        // Rendre la fenêtre visible
        frame.setVisible(true);

    }
    
}
